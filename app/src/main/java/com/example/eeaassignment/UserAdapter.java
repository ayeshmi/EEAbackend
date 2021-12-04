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

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAdapter extends BaseAdapter {
    private List<User> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    public UserAdapter(Context aContext, List<User> listData) {
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
        ViewHolder holder;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
            holder.uName = (TextView) v.findViewById(R.id.name);
            holder.uDesignation = (TextView) v.findViewById(R.id.designation);
            holder.view=(Button)v.findViewById(R.id.view);
            holder.delete=(Button)v.findViewById(R.id.delete);
           // holder.uLocation = (TextView) v.findViewById(R.id.location);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.view.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Log.d("myTag", "Called the function"+listData.get(position).getId());


                         viewSelectedUserDetails(context,listData.get(position).getId().toString());
                    }
                }
        );
        holder.delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("myTag", "Called the function"+listData.get(position).getId());
                        Long id=listData.get(position).getId();
                        Call<ResponseBody> loginResponseCall = ApiClient.getUserService().deleteUser(id);
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

        holder.uName.setText(listData.get(position).getUsername());
        holder.uDesignation.setText(listData.get(position).getEmail());
        //holder.uLocation.setText(listData.get(position).getBirthday());
        return v;
    }
    static class ViewHolder {
        Button view;
        Button delete;
        TextView uName;
        TextView uDesignation;
        TextView uLocation;
    }

    private void viewSelectedUserDetails(Context context,String id) {
        Intent intent=new Intent(context,UpdateItemDetails.class);
        intent.putExtra("itemId", id);
        context.startActivity(intent);
    }
}