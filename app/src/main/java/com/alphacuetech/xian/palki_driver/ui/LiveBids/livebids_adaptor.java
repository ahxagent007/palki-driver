package com.alphacuetech.xian.palki_driver.ui.LiveBids;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.alphacuetech.xian.palki_driver.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class livebids_adaptor  extends FirebaseRecyclerAdapter<dataModel,livebids_adaptor.livebids_viewholder>
{

    public livebids_adaptor(@NonNull FirebaseRecyclerOptions<dataModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull livebids_viewholder holder, int position, @NonNull dataModel dataModel) {
        holder.auctionId.setText(dataModel.getAuction_id());
        holder.fromAddress.setText(dataModel.getFrom());
        holder.toAddress.setText(dataModel.getTo());
        holder.carType.setText(dataModel.getVehicle());
    }

    @NonNull
    @Override
    public livebids_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.livebids_recyleview_design,parent,false);
        return new livebids_viewholder(view);
    }

    public class livebids_viewholder extends RecyclerView.ViewHolder{
        TextView auctionId, fromAddress, toAddress, carType;
        public livebids_viewholder(@NonNull View itemView) {
            super(itemView);

            auctionId = itemView.findViewById(R.id.auctionId);
            fromAddress = itemView.findViewById(R.id.fromAddress);
            toAddress = itemView.findViewById(R.id.toAddress);
            carType = itemView.findViewById(R.id.cardType);
        }
    }
}
