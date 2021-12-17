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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eeaassignment.service.ApiClient;
import com.example.eeaassignment.R;
import com.example.eeaassignment.UpdateItemDetails;
import com.example.eeaassignment.model.User;
import com.example.eeaassignment.ViewAllUser;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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

            holder.view=(Button)v.findViewById(R.id.view);
            holder.image=(ImageView) v.findViewById(R.id.img);
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
                                    Intent intent=new Intent(context, ViewAllUser.class);
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

       // String imageURL=listData.get(position)
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

        holder.uName.setText(listData.get(position).getUsername());


        //holder.uLocation.setText(listData.get(position).getBirthday());
        return v;
    }
    static class ViewHolder {
        Button view;
        Button delete;
        TextView uName;
        TextView uDesignation;
        TextView uLocation;
        ImageView image;
    }

    private void viewSelectedUserDetails(Context context,String id) {
        Intent intent=new Intent(context, UpdateItemDetails.class);
        intent.putExtra("itemId", id);
        context.startActivity(intent);
    }


}