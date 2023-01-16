package com.alphacuetech.xian.palki_driver.ui.LiveBids;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.alphacuetech.xian.palki_driver.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class livebids_adaptor  extends RecyclerView.Adapter<livebids_adaptor.livebids_viewholder>
{

    private List<dataModel> bidList = new ArrayList<>();
    private Context mContext;
    private FirebaseDatabase db;
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
        private TextInputLayout offer_amount;
        public livebids_viewholder(@NonNull View itemView) {
            super(itemView);
            carType = itemView.findViewById(R.id.cartypId);
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

                    if(amount.isEmpty())
                    {
                        offer_amount.setError("Amount Field Can't be Empty !!");
                        offer_amount.requestFocus();
                        return;
                    } else if(!amount.isEmpty()){
                        offer_amount.setError(null);
                    }
//                    Log.d("star", String.valueOf(bidList.get(position).getAuction_id()));
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
                                  boolean is_Successful = create_offer(auc_id, Integer.parseInt(amount), carCondition, carModel, dirver_id, rating, seatCap);
                                    if(is_Successful){
                                        Toast.makeText(mContext,"You have successfully submit a offer.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(mContext,"Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).create().show();

                }
            });
        }
    }

        private boolean create_offer(String auctionId, int bidAmount, String carCondition, String carModel, String driver_id, String rating, String seatCap) {

            db = FirebaseDatabase.getInstance();
            String bidId = db.getReference().child(String.valueOf(auctionId)).push().getKey();
            DatabaseReference createOffer = db.getReference().child("BIDS2").child(String.valueOf(auctionId)).child(bidId);

            createOffer.child("auctionID").setValue(auctionId);
            createOffer.child("bidAmount").setValue(bidAmount);
            createOffer.child("bidID").setValue(bidId);
            createOffer.child("carType").setValue(carModel);
            createOffer.child("condition").setValue(carCondition);
            createOffer.child("driverID").setValue(driver_id);
            createOffer.child("rating").setValue(rating);
            createOffer.child("seatCap").setValue(seatCap);
            return true;
        }

}
