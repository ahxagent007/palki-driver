package com.alphacuetech.xian.palki_driver.ui.LiveBids;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.alphacuetech.xian.palki_driver.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class livebids_adaptor  extends RecyclerView.Adapter<livebids_adaptor.livebids_viewholder>
{

    private List<dataModel> bidList = new ArrayList<>();
    private final Context mContext;
    private FirebaseDatabase db;
    private FirebaseAuth mAuth;

    public livebids_adaptor(List<dataModel> list, Context context){
        bidList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public livebids_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.livebids_recyleview_design,parent,false);
        return new livebids_viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull livebids_viewholder holder, int position) {
        holder.carType.setText(bidList.get(position).getCarType());
        holder.fromAddress.setText("From: "+bidList.get(position).getCurrentLoc());
        holder.toAddress.setText("To: "+bidList.get(position).getDestLoc());
        holder.seatCap.setText("Seat : "+bidList.get(position).getSeatcap());
        holder.dateTime.setText("Date : "+bidList.get(position).getDate_() + " ( " +bidList.get(position).getTime_() + " )");
    }

    @Override
    public int getItemCount() {
        return bidList.size();
    }

    public class livebids_viewholder extends RecyclerView.ViewHolder{
        TextView seatCap, fromAddress, toAddress, carType, dateTime;
        int position;
        private final TextInputLayout offer_amount;
        public livebids_viewholder(@NonNull View itemView) {
            super(itemView);
            carType = itemView.findViewById(R.id.carType);
            fromAddress = itemView.findViewById(R.id.fromAddress);
            toAddress = itemView.findViewById(R.id.toAddress);
            seatCap = itemView.findViewById(R.id.seatCap);
            dateTime = itemView.findViewById(R.id.dateTime);
            offer_amount = itemView.findViewById(R.id.offer_amount);
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            itemView.findViewById(R.id.btn_makeorder).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String amount = offer_amount.getEditText().getText().toString().trim();
                    position =getAdapterPosition();

                    mAuth = FirebaseAuth.getInstance();
                    String currentUserID = mAuth.getCurrentUser().getUid();
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference reference = db.getReference().child("BIDRESULT2");
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Map value =(Map) dataSnapshot.getValue();
                            boolean isReturn = false;

                            for (Object key : value.keySet()) {
                                Map driverIdMap =(Map) value.get(key);
                                String driverId =(String) driverIdMap.get("driver_id");
                                if(driverId.equals(currentUserID)){
                                    new AlertDialog.Builder(mContext)
                                            .setTitle("Alert")
                                            .setMessage("Please Complete Your Current Trip.")
                                            .setNegativeButton(android.R.string.ok, null).create().show();

                                    isReturn = true;
                                    break;
                                }
                            }

                            if(isReturn){
                                offer_amount.getEditText().setText(null);
                                offer_amount.clearFocus();
                                InputMethodManager imm = (InputMethodManager) ContextCompat.getSystemService(mContext, InputMethodManager.class);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                return;
                            }   else if(amount.isEmpty()) {
                                offer_amount.setError("Amount Field Can't be Empty !!");
                                offer_amount.requestFocus();
                                return;
                            } else if(!amount.isEmpty()){
                                offer_amount.setError(null);
                            }
                            dataModel select_item = bidList.get(position);
                            String auc_id = select_item.getAuction_id();
                            String carCondition = "ac";
                            String carModel = select_item.getCarType();
                            String dirver_id = user.getUid();
                            String rating = "4.8";
                            String seatCap = select_item.getSeatcap();

                            new AlertDialog.Builder(mContext)
                                    .setTitle("Confirm")
                                    .setMessage("Are You confirm to submit the Offer ?")
                                    .setNegativeButton(android.R.string.cancel, null)
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface arg0, int arg1) {
                                            boolean is_Successful = create_offer(auc_id, amount, carCondition, carModel, dirver_id, rating, seatCap);
                                            if(is_Successful){
                                                offer_amount.getEditText().setText(null);
                                                offer_amount.clearFocus();
                                                InputMethodManager imm = (InputMethodManager) ContextCompat.getSystemService(mContext, InputMethodManager.class);
                                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                                            } else {
                                                Toast.makeText(mContext,"Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }).create().show();


                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Handle the error
                        }
                    });



                }
            });
        }
    }

    private boolean create_offer(String auctionId, String bidAmount, String carCondition, String carModel, String driver_id, String rating, String seatCap) {
        db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference().child("BIDS2").child(auctionId);

        mAuth = FirebaseAuth.getInstance();
        String currentUserId = mAuth.getCurrentUser().getUid();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                long childCount = snapshot.getChildrenCount();
                Log.d("count****",String.valueOf(childCount));

                if(childCount == 0){
                    db = FirebaseDatabase.getInstance();
                    String bidId = db.getReference().child(String.valueOf(auctionId)).push().getKey();
                    DatabaseReference createOffer = db.getReference().child("BIDS2").child(auctionId).child(bidId);

                    createOffer.child("auctionID").setValue(auctionId);
                    createOffer.child("bidAmount").setValue(bidAmount);
                    createOffer.child("bidID").setValue(bidId);
                    createOffer.child("carType").setValue(carModel);
                    createOffer.child("condition").setValue(carCondition);
                    createOffer.child("driverID").setValue(driver_id);
                    createOffer.child("rating").setValue(rating);
                    createOffer.child("seatCap").setValue(seatCap);
                    new AlertDialog.Builder(mContext)
                            .setTitle("Congratulations")
                            .setMessage("You have Successfully Submit a Offer for this Auction.")
                            .setNegativeButton(android.R.string.ok, null).create().show();
                    return;
                }
                long i =1;
                for (DataSnapshot child : snapshot.getChildren()) {
                    Log.d("i****",String.valueOf(i));
                    Map driverIdMap =(Map) child.getValue();
                    String driverId =(String) driverIdMap.get("driverID");
                    if(driverId.equals(currentUserId)){
                        new AlertDialog.Builder(mContext)
                                .setTitle("Error")
                                .setMessage("You have already Submit A offer for this Auction.")
                                .setNegativeButton(android.R.string.ok, null).create().show();
                        break;
                    }else if(childCount == i){
                        db = FirebaseDatabase.getInstance();
                        String bidId = db.getReference().child(String.valueOf(auctionId)).push().getKey();
                        DatabaseReference createOffer = db.getReference().child("BIDS2").child(auctionId).child(bidId);

                        createOffer.child("auctionID").setValue(auctionId);
                        createOffer.child("bidAmount").setValue(bidAmount);
                        createOffer.child("bidID").setValue(bidId);
                        createOffer.child("carType").setValue(carModel);
                        createOffer.child("condition").setValue(carCondition);
                        createOffer.child("driverID").setValue(driver_id);
                        createOffer.child("rating").setValue(rating);
                        createOffer.child("seatCap").setValue(seatCap);
                        new AlertDialog.Builder(mContext)
                                .setTitle("Congratulations")
                                .setMessage("You have Successfully Submit a Offer for this Auction.")
                                .setNegativeButton(android.R.string.ok, null).create().show();
                    }
                    i++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return true;
    }

}
