package com.example.eeaassignment.adapter;

import android.content.Context;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.example.eeaassignment.service.ApiClient;
import com.example.eeaassignment.model.Order;
import com.example.eeaassignment.R;
import com.example.eeaassignment.ViewCart;
import com.example.eeaassignment.ViewSelectedItemOrder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends BaseAdapter {
    private List<Order> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    public CartAdapter(Context aContext, List<Order> listData) {
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
        CartAdapter.ViewHolder holder;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.cart_details, null);
            holder = new CartAdapter.ViewHolder();
            holder.uName = (TextView) v.findViewById(R.id.name);
            holder.uDesignation = (TextView) v.findViewById(R.id.totaPrice);
            holder.img=(ImageView) v.findViewById(R.id.img);

            holder.delete=(Button)v.findViewById(R.id.delete);
            holder.cardView= (CardView)v.findViewById(R.id.card);
            // holder.uLocation = (TextView) v.findViewById(R.id.location);
            v.setTag(holder);
        } else {
            holder = (CartAdapter.ViewHolder) v.getTag();

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

                        Long id=listData.get(position).getId();
                        Call<ResponseBody> loginResponseCall = ApiClient.getOrderService().cancelOrder(id);
                        loginResponseCall.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                if(response.isSuccessful()){

                                    Toast.makeText(context, "An error occurred while deleting the lecture", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(context, ViewCart.class);
                                    context.startActivity(intent);
                                }else{

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
        holder.uDesignation.setText(listData.get(position).getTotalPrice()+".00");
        Bitmap bimage=null;
        InputStream in= null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL("http://192.168.1.3:8080/api/auth/video/"+listData.get(position).getImageName());
            bimage  = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            holder.img.setImageBitmap(bimage);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return v;
    }
    static class ViewHolder {
        TextView uName;
        TextView uDesignation;
        TextView uLocation;
        Button view;
        Button delete;
        CardView cardView;
        ImageView img;

    }

    private void viewSelectedItemDetails(Context context,String id) {
        Log.d("myTag", "Item is deleted122j"+id);
        Intent intent=new Intent(context, ViewSelectedItemOrder.class);
        intent.putExtra("itemId", id);
        context.startActivity(intent);
    }
}
