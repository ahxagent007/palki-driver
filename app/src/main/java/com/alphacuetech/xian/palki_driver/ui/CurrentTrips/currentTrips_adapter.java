package com.alphacuetech.xian.palki_driver.ui.CurrentTrips;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.alphacuetech.xian.palki_driver.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class currentTrips_adapter extends RecyclerView.Adapter<currentTrips_adapter.CurrentTripsViwModel> {

    private List<currentTripsModel> currentTripsList = new ArrayList<>();
    private Context mContext;
    private FirebaseDatabase db;
    private FirebaseAuth mAuth;
    public currentTrips_adapter(List<currentTripsModel> list, Context context){
        currentTripsList = list;
        mContext = context;

    }

    @NonNull
    @Override
    public CurrentTripsViwModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currenttrips_design,parent,false);
        return new CurrentTripsViwModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentTripsViwModel holder, int position) {
        String currentUserID = currentTripsList.get(position).getUser_id();

        String url = "https://dolnabd.com/api/Rider/Get/Details/"+currentUserID;
        MyAsyncTask task = new MyAsyncTask(url,holder);
        task.execute();

        String userPhoneNumberStr = "017++++++";
        holder.userName.setText("User Name");
        holder.userPhoneNumber.setText(userPhoneNumberStr);
        holder.fromAddress.setText("From: "+currentTripsList.get(position).getFrom());
        holder.toAddress.setText("To: "+currentTripsList.get(position).getTo());
        holder.price.setText("Amount: "+String.valueOf(currentTripsList.get(position).getAmount()));
    }

    @Override
    public int getItemCount() {
        return currentTripsList.size();
    }

    public class CurrentTripsViwModel extends RecyclerView.ViewHolder{
        int position;
        TextView userName,userPhoneNumber, fromAddress, toAddress, price;
        public CurrentTripsViwModel(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.userName);
            userPhoneNumber = itemView.findViewById(R.id.userNumber);
            fromAddress = itemView.findViewById(R.id.fromAddress);
            toAddress = itemView.findViewById(R.id.toAddress);
            price = itemView.findViewById(R.id.price);

            itemView.findViewById(R.id.openMap_button_currenttrips).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    position = getAdapterPosition();
                    String lat_lng = currentTripsList.get(position).getTo_latLng();

                    lat_lng =(lat_lng.split(":"))[1].replaceAll("[()]","");

                    String latitude = (lat_lng.split(","))[0];
                    String longitude = (lat_lng.split(","))[1];
                    String label = currentTripsList.get(position).getTo();

                    new AlertDialog.Builder(mContext)
                            .setTitle("Confirm")
                            .setMessage("Are You Sure to Open Map?")
                            .setNegativeButton(android.R.string.cancel, null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {
                                    Uri mapUri = Uri.parse("geo:0,0?q="+latitude+","+longitude+"("+label+")");
                                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                                    mapIntent.setPackage("com.google.android.apps.maps");
                                    mContext.startActivity(mapIntent);
                                }
                            }).create().show();


                }
            });

            itemView.findViewById(R.id.complete_button_currenttrips).setOnClickListener(new View.OnClickListener() {
                @SuppressLint("LongLogTag")
                @Override
                public void onClick(View view) {
                    position = getAdapterPosition();
                    currentTripsModel select_item = currentTripsList.get(position);
                    String auctionId_done = select_item.getAuction_id();
                   // String user_id = getUserID(auctionId_done);
                    Log.d("checkk**********************8",auctionId_done);
                    new AlertDialog.Builder(mContext)
                            .setTitle("Mark As Complete")
                            .setMessage("Are You Sure to Mark The Ride As Complete ?")
                            .setNegativeButton(android.R.string.cancel, null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {
                                    db = FirebaseDatabase.getInstance();
                                    DatabaseReference createOffer = db.getReference().child("BIDRESULT2");
                                    createOffer.child(auctionId_done).removeValue();
                                    notifyDataSetChanged();

                                    DatabaseReference updateAuc = db.getReference().child("AUCTION2").child(auctionId_done);
                                    updateAuc.child("running").setValue("No");
                                    notifyDataSetChanged();
                                }
                            }).create().show();


                };


            });
        }

    }

    private class MyAsyncTask extends AsyncTask<Void, Void, String> {
        private String url;
        @NonNull CurrentTripsViwModel holder;

        MyAsyncTask(String url,@NonNull CurrentTripsViwModel holder) {
            this.url = url;
            this.holder = holder;
        }

        @Override
        protected String doInBackground(Void... params) {
            String responseBody = null;
    
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Call call = client.newCall(request);
            try (Response response = call.execute()) {
                if (response.isSuccessful()) {
                    responseBody = response.body().string();
                    // do something with the response
                } else {
                    // handle error
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
          

            return responseBody;
        }

        @Override
        protected void onPostExecute(String result) {
            // update UI with the result
            try {
                JSONObject obj = new JSONObject(result);
                holder.userName.setText(obj.getString("Name"));
                holder.userPhoneNumber.setText(obj.getString("Phone"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}




