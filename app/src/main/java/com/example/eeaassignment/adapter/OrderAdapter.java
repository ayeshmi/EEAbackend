package com.example.eeaassignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.example.eeaassignment.service.ApiClient;
import com.example.eeaassignment.model.Order;
import com.example.eeaassignment.R;
import com.example.eeaassignment.ViewOrderDetails;
import com.example.eeaassignment.ViewSelectedItemOrder;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderAdapter extends BaseAdapter {
    private List<Order> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    public OrderAdapter(Context aContext, List<Order> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
        context=aContext;
    }
    @Override
    public int getCount() {
        return listData.size();
    }
    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View v, ViewGroup vg) {
        OrderAdapter.ViewHolder holder;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.order_details, null);
            holder = new OrderAdapter.ViewHolder();
            holder.uName = (TextView) v.findViewById(R.id.name);
            holder.uDesignation = (TextView) v.findViewById(R.id.designation);
            holder.complete=(Button)v.findViewById(R.id.complete);
            holder.delete=(Button)v.findViewById(R.id.delete);
            holder.cardView= (CardView)v.findViewById(R.id.card);
            // holder.uLocation = (TextView) v.findViewById(R.id.location);
            v.setTag(holder);
        } else {
            holder = (OrderAdapter.ViewHolder) v.getTag();

        }

        holder.cardView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("myTag", "Called the function"+listData.get(position).getId());


                        viewSelectedItemDetails(context,listData.get(position).getItemId().toString());
                    }
                }
        );

        holder.delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Log.d("myTag", "Called the function"+listData.get(position).getId());
                        Long id=listData.get(position).getId();
                        Call<ResponseBody> loginResponseCall = ApiClient.getOrderService().cancelOrder(id);
                        loginResponseCall.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                if(response.isSuccessful()){
                                    Log.d("myTag", "This is my message123");
                                    Toast.makeText(context, "An error occurred while deleting the lecture", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(context, ViewOrderDetails.class);
                                    context.startActivity(intent);
                                }else{
                                    Log.d("myTag", "This is my message123dsdsd");
                                    Toast.makeText(context, "An error occurred while deleting the lecture", Toast.LENGTH_SHORT).show();
                                }

                            }
                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                                Log.d("myTag", "This is my messageFail");
                            }
                        });
                        // Toast.makeText(holder.this,"Username / Password Required", Toast.LENGTH_LONG).show();
                        Log.d("myTag", "Item is deleted");
                    }
                }
        );

        holder.complete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Log.d("myTag", "Called the function"+listData.get(position).getId());
                        Long id=listData.get(position).getId();
                        Call<ResponseBody> loginResponseCall = ApiClient.getOrderService().comleteOrder(id);
                        loginResponseCall.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                if(response.isSuccessful()){
                                    Log.d("myTag", "This is my message123");
                                    Toast.makeText(context, "An error occurred while deleting the lecture", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(context,ViewOrderDetails.class);
                                    context.startActivity(intent);
                                }else{
                                    Log.d("myTag", "This is my message123dsdsd");
                                    Toast.makeText(context, "An error occurred while deleting the lecture", Toast.LENGTH_SHORT).show();
                                }

                            }
                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                                Log.d("myTag", "This is my messageFail");
                            }
                        });
                        // Toast.makeText(holder.this,"Username / Password Required", Toast.LENGTH_LONG).show();
                        Log.d("myTag", "Item is deleted");
                    }
                }
        );

        holder.uName.setText(listData.get(position).getName());
        holder.uDesignation.setText(listData.get(position).getTotalPrice());
        //holder.uLocation.setText(listData.get(position).getBirthday());
        return v;
    }
    static class ViewHolder {
        TextView uName;
        TextView uDesignation;
        TextView uLocation;
        Button complete;
        Button delete;
        CardView cardView;

    }

    private void viewSelectedItemDetails(Context context,String id) {
        Log.d("myTag", "Item is deleted122j"+id);
        Intent intent=new Intent(context, ViewSelectedItemOrder.class);
        intent.putExtra("itemId", id);
        context.startActivity(intent);
    }
}
