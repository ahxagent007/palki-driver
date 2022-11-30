package com.alphacuetech.xian.palki_driver.ui.LiveBids;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.alphacuetech.xian.palki_driver.Activities.loggin;
import com.alphacuetech.xian.palki_driver.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        holder.auctionId.setText("Id: " + bidList.get(position).getAuction_id());
        holder.fromAddress.setText("From: "+bidList.get(position).getFrom());
        holder.toAddress.setText("To: "+bidList.get(position).getTo());
        holder.carType.setText("Car Type: "+bidList.get(position).getVehicle());
    }

    @Override
    public int getItemCount() {
        return bidList.size();
    }

    public class livebids_viewholder extends RecyclerView.ViewHolder{
        TextView auctionId, fromAddress, toAddress, carType;
        int position;
        private TextInputLayout offer_amount;
        public livebids_viewholder(@NonNull View itemView) {
            super(itemView);
            auctionId = itemView.findViewById(R.id.auctionId);
            fromAddress = itemView.findViewById(R.id.fromAddress);
            toAddress = itemView.findViewById(R.id.toAddress);
            carType = itemView.findViewById(R.id.cardType);
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
                    long auc_id = select_item.getAuction_id();
                    boolean is_AC = true;
                    String car_cond = "Good";
                    String carModel = select_item.getVehicle();
                    String carReg = "Dhaka-KHA-1207";
                    String dirver_id = user.getUid();
                    String driver_email = user.getEmail();

                    new AlertDialog.Builder(mContext)
                            .setTitle("Confirm")
                            .setMessage("Are You confirm to submit the Offer ?")
                            .setNegativeButton(android.R.string.cancel, null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1) {
                                  boolean is_Successful = create_offer(auc_id, Integer.parseInt(amount), is_AC, car_cond, carModel, carReg,dirver_id,driver_email);
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

        private boolean create_offer(long auctionId, int bidAmount, boolean is_carAc, String car_condition, String carModel, String car_registrarion_number,String driver_id, String driver_email) {

            db = FirebaseDatabase.getInstance();
            DatabaseReference createOffer = db.getReference().child("BIDS").child(String.valueOf(auctionId)).child(driver_id);

            createOffer.child("auction_id").setValue(auctionId);
            createOffer.child("bid_amount").setValue(bidAmount);
            createOffer.child("car_ac").setValue(is_carAc);
            createOffer.child("car_condition").setValue(car_condition);
            createOffer.child("car_model").setValue(carModel);
            createOffer.child("car_registrarion_number").setValue(car_registrarion_number);
            createOffer.child("dirver_email_address").setValue(driver_email);
            return true;
        }

}
