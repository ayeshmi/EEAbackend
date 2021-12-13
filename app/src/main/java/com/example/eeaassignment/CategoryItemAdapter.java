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
        CategoryItemAdapter.ViewHolder holder;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.view_category_item, null);
            holder = new CategoryItemAdapter.ViewHolder();
            holder.uName = (TextView) v.findViewById(R.id.name);
            holder.uDesignation = (TextView) v.findViewById(R.id.designation);
            holder.view=(Button)v.findViewById(R.id.view);
            holder.delete=(Button)v.findViewById(R.id.delete);
            // holder.uLocation = (TextView) v.findViewById(R.id.location);
            v.setTag(holder);
        } else {
            holder = (CategoryItemAdapter.ViewHolder) v.getTag();

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
        Intent intent=new Intent(context,ViewSelectedItemOrder.class);
        intent.putExtra("itemId", id);
        context.startActivity(intent);
    }

}
