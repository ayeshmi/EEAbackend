package com.example.eeaassignment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private List<User> listData;
    private LayoutInflater layoutInflater;
    public CustomListAdapter(Context aContext, List<User> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
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
           // holder.uLocation = (TextView) v.findViewById(R.id.location);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.uName.setText(listData.get(position).getUsername());
        holder.uDesignation.setText(listData.get(position).getEmail());
        //holder.uLocation.setText(listData.get(position).getBirthday());
        return v;
    }
    static class ViewHolder {
        TextView uName;
        TextView uDesignation;
        TextView uLocation;
    }
}