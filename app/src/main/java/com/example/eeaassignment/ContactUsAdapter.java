package com.example.eeaassignment;

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

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsAdapter extends BaseAdapter {
    private List<ContactUsRequest> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    public ContactUsAdapter(Context aContext, List<ContactUsRequest> listData) {
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
        ItemAdapter.ViewHolder holder;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.view_contactus, null);
            holder = new ItemAdapter.ViewHolder();
            holder.uName = (TextView) v.findViewById(R.id.name);
            holder.uDesignation = (TextView) v.findViewById(R.id.designation);
            holder.view=(Button)v.findViewById(R.id.view);
            holder.delete=(Button)v.findViewById(R.id.delete);
            // holder.uLocation = (TextView) v.findViewById(R.id.location);
            v.setTag(holder);
        } else {
            holder = (ItemAdapter.ViewHolder) v.getTag();

        }
        holder.view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("myTag", "Called the function"+listData.get(position).getId());

                        //  Toast.makeText(com.example.eeaassignment.ViewAllItems.this,"Username / Password Required", Toast.LENGTH_LONG).show();
                        viewSelectedItemDetails(context,listData.get(position).getId().toString());
                    }
                }
        );
        holder.delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("myTag", "Called the function"+listData.get(position).getId());
                        Long id=listData.get(position).getId();
                        Call<ResponseBody> loginResponseCall = ApiClient.getItemService().deleteItem(id);
                        loginResponseCall.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                if(response.isSuccessful()){
                                    Log.d("myTag", "This is my message123");
                                    Toast.makeText(context, "An error occurred while deleting the lecture", Toast.LENGTH_SHORT).show();

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
        holder.uDesignation.setText(listData.get(position).getName());
        //holder.uLocation.setText(listData.get(position).getBirthday());
        return v;
    }
    static class ViewHolder {
        TextView uName;
        TextView uDesignation;
        TextView uLocation;
        Button view;
        Button delete;

    }

    private void viewSelectedItemDetails(Context context,String id) {
        Intent intent=new Intent(context,UpdateItemDetails.class);
        intent.putExtra("itemId", id);
        context.startActivity(intent);
    }


}


