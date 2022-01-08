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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.eeaassignment.R;
import com.example.eeaassignment.ViewSelectedItemOrder;
import com.example.eeaassignment.model.Item;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class CategoryItemAdapter extends BaseAdapter {
    private List<Item> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    public CategoryItemAdapter(Context aContext, List<Item> listData) {
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
            v = layoutInflater.inflate(R.layout.view_category_item, null);
            holder = new ViewHolder();
            holder.uName = (TextView) v.findViewById(R.id.name);
            holder.uDesignation = (TextView) v.findViewById(R.id.designation);
            holder.image=(ImageView) v.findViewById(R.id.img);
            holder.cardView=(CardView) v.findViewById(R.id.card);
            holder.itemType=(TextView) v.findViewById(R.id.itemType);
            holder.availability=(TextView) v.findViewById(R.id.availability);
            // holder.uLocation = (TextView) v.findViewById(R.id.location);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();

        }

        holder.cardView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("myTag", "Called the function"+listData.get(position).getId());

                        //  Toast.makeText(com.example.eeaassignment.ViewAllItems.this,"Username / Password Required", Toast.LENGTH_LONG).show();
                        viewSelectedItemDetails(context,listData.get(position).getId().toString());
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

        holder.uName.setText(listData.get(position).getName());
        holder.uDesignation.setText(listData.get(position).getPrice());
        holder.itemType.setText(listData.get(position).getItemType());
        holder.availability.setText(listData.get(position).getAvailability());
        return v;
    }
    static class ViewHolder {
        TextView uName;
        TextView uDesignation;
        TextView uLocation;
        ImageView image;
        CardView cardView;
        TextView itemType;
        TextView availability;

    }

    private void viewSelectedItemDetails(Context context,String id) {
        Intent intent=new Intent(context, ViewSelectedItemOrder.class);
        intent.putExtra("itemId", id);
        context.startActivity(intent);
    }

}
