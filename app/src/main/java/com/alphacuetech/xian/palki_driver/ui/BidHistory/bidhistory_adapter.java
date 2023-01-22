package com.alphacuetech.xian.palki_driver.ui.BidHistory;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.alphacuetech.xian.palki_driver.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class bidhistory_adapter extends RecyclerView.Adapter<bidhistory_adapter.BidHistoryViwModel> {

    private List<bidhistoryModel> bidhistory = new ArrayList<>();
    private Context mContext;
    private FirebaseDatabase db;
    public bidhistory_adapter(List<bidhistoryModel> list, Context context){
        bidhistory = list;
        mContext = context;

    }

    @NonNull
    @Override
    public BidHistoryViwModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bidhistory_design,parent,false);
        return new BidHistoryViwModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BidHistoryViwModel holder, @SuppressLint("RecyclerView") int position) {
        String auctionID = bidhistory.get(position).getAuctionID();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference reference = db.getReference().child("AUCTION2").child(auctionID);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Map map = (Map) snapshot.getValue();
                String carType ="Car Type : " + (String) map.get("carType");
                String fromAddress ="From : " + (String) map.get("currentLoc");
                String toAddress ="To : " +(String) map.get("destLoc");
                String seatCap ="Seat : " + (String) map.get("seatcap");
                String bidamount ="Amount : " + bidhistory.get(position).getBidAmount();
                String arrivalDate ="Date : " + (String) map.get("date_") + " ( " + (String) map.get("time_") + " )";


                holder.carType.setText(carType);
                holder.fromAddress.setText(fromAddress);
                holder.toAddress.setText(toAddress);
                holder.seatCap.setText(seatCap);
                holder.bidAmount.setText(bidamount);
                holder.arrivalDate.setText(arrivalDate);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return bidhistory.size();
    }

    public class BidHistoryViwModel extends RecyclerView.ViewHolder{
        int position;
        TextView fromAddress, toAddress, bidAmount, carType, seatCap, arrivalDate;
        public BidHistoryViwModel(@NonNull View itemView) {
            super(itemView);

            carType = itemView.findViewById(R.id.carType);
            seatCap = itemView.findViewById(R.id.seatCap);
            arrivalDate = itemView.findViewById(R.id.dateTime);
            fromAddress = itemView.findViewById(R.id.fromAddress);
            toAddress = itemView.findViewById(R.id.toAddress);
            bidAmount = itemView.findViewById(R.id.bidAmount);

            itemView.findViewById(R.id.btn_deleteoffer).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    position = getAdapterPosition();
                    bidhistoryModel select_item = bidhistory.get(position);
                    String auc_id = select_item.getAuctionID();
                    String bidId = select_item.getBidID();

                    new AlertDialog.Builder(mContext)
                            .setTitle("Confirm")
                            .setMessage("Are You Sure to Delete Your Offer ?")
                            .setNegativeButton(android.R.string.cancel, null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1) {
                                    db = FirebaseDatabase.getInstance();
                                    DatabaseReference deleteOffer = db.getReference().child("BIDS2").child(auc_id);
                                    deleteOffer.child(bidId).removeValue();
                                    notifyDataSetChanged();
                                }
                            }).create().show();


                }
            });

        }
    }
}




