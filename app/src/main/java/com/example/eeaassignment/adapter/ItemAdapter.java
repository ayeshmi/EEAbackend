package com.example.eeaassignment.adapter;

import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.eeaassignment.service.ApiClient;
import com.example.eeaassignment.model.Item;
import com.example.eeaassignment.R;
import com.example.eeaassignment.UpdateItemDetails;
import com.example.eeaassignment.ViewAllItems;


public class ItemAdapter extends BaseAdapter {
    private List<Item> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    public ItemAdapter(Context aContext, List<Item> listData) {
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
            v = layoutInflater.inflate(R.layout.view_items_list, null);
            holder = new ItemAdapter.ViewHolder();
            holder.uName = (TextView) v.findViewById(R.id.name);
            holder.view=(Button)v.findViewById(R.id.view);
            holder.delete=(Button)v.findViewById(R.id.delete);
            holder.image=(ImageView) v.findViewById(R.id.img);
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

                        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage("Are you sure you want to delete this item  ?"  );
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        Long id=listData.get(position).getId();
                                        Call<ResponseBody> loginResponseCall = ApiClient.getItemService().deleteItem(id);
                                        loginResponseCall.enqueue(new Callback<ResponseBody>() {
                                            @Override
                                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                                if(response.isSuccessful()){

                                                    Toast.makeText(context, "Item is successfully deleted.", Toast.LENGTH_SHORT).show();
                                                    Intent intent=new Intent(context, ViewAllItems.class);
                                                    context.startActivity(intent);
                                                }else{

                                                    Toast.makeText(context, "An error occurred while deleting the item", Toast.LENGTH_SHORT).show();

                                                }

                                            }
                                            @Override
                                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                                Toast.makeText(context, "Something went wrong check again.", Toast.LENGTH_SHORT).show();

                                            }


                                        });
                                        // Toast.makeText(holder.this,"Username / Password Required", Toast.LENGTH_LONG).show();
                                        Log.d("myTag", "Item is deleted");

                                    }
                                });
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();


                    }
                }
        );

        String imageURL=listData.get(position).getImageName();
        Bitmap bimage=null;
        InputStream in= null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL("http://192.168.1.3:8080/api/auth/video/"+imageURL);
            bimage  = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            holder.image.setImageBitmap(bimage);

        } catch (IOException e) {
            e.printStackTrace();
        }
        holder.uName.setText(listData.get(position).getName());

        //holder.uLocation.setText(listData.get(position).getBirthday());
        return v;
    }
    static class ViewHolder {
        TextView uName;
        TextView uDesignation;
        TextView uLocation;
        Button view;
        Button delete;
        ImageView image;

    }

    private void viewSelectedItemDetails(Context context,String id) {
        Intent intent=new Intent(context, UpdateItemDetails.class);
        intent.putExtra("itemId", id);
        context.startActivity(intent);
    }


}
