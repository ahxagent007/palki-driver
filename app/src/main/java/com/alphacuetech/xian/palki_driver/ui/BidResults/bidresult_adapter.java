package com.alphacuetech.xian.palki_driver.ui.BidResults;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alphacuetech.xian.palki_driver.R;
import com.alphacuetech.xian.palki_driver.ui.LiveBids.dataModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class bidresult_adapter extends RecyclerView.Adapter<bidresult_adapter.BidresultViwModel> {

    private List<bidresultModel> bidresultList = new ArrayList<>();
    private Context mContext;
    private FirebaseDatabase db;
    public bidresult_adapter(List<bidresultModel> list, Context context){
        bidresultList = list;
        mContext = context;

    }

    @NonNull
    @Override
    public BidresultViwModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bidresults_design,parent,false);
        return new BidresultViwModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BidresultViwModel holder, int position) {
        holder.auctionId.setText(String.valueOf(bidresultList.get(position).getAuction_id()));
        holder.fromAddress.setText(bidresultList.get(position).getFrom());
        holder.toAddress.setText(bidresultList.get(position).getTo());
        holder.price.setText(String.valueOf(bidresultList.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return bidresultList.size();
    }

    public class BidresultViwModel extends RecyclerView.ViewHolder{
        int position;
        TextView auctionId, fromAddress, toAddress, price;
        public BidresultViwModel(@NonNull View itemView) {
            super(itemView);

            auctionId = itemView.findViewById(R.id.auctionId);
            fromAddress = itemView.findViewById(R.id.fromAddress);
            toAddress = itemView.findViewById(R.id.toAddress);
            price = itemView.findViewById(R.id.price);

            itemView.findViewById(R.id.confirm_button_bidresult).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    position = getAdapterPosition();
                    bidresultModel select_item = bidresultList.get(position);
                    long auc_id = select_item.getAuction_id();

                    db = FirebaseDatabase.getInstance();
                    DatabaseReference createOffer = db.getReference().child("BIDRESULTS").child(String.valueOf(auc_id));
                    createOffer.child("confirm").setValue(true);
                }
            });
        }
    }
}




