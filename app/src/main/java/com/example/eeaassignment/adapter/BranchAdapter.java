package com.example.eeaassignment.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

import com.example.eeaassignment.Employee;
import com.example.eeaassignment.R;
import com.example.eeaassignment.StudentsProvider;

import java.util.List;

public class BranchAdapter extends BaseAdapter {
    private List<Employee> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    private String name;
    private EditText editText;
    public static final Uri CONTENT_URI1= Uri.parse("content://com.example.eeaassignment.StudentsProvider/students/20");
    public BranchAdapter(Context aContext, List<Employee> listData) {
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
        BranchAdapter.ViewHolder holder;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.employee, null);
            holder = new BranchAdapter.ViewHolder();
            holder.uName = (TextView) v.findViewById(R.id.name);
            holder.uDesignation = (TextView) v.findViewById(R.id.designation);
            holder.img=(ImageView) v.findViewById(R.id.img);
            holder.view=(Button)v.findViewById(R.id.view);
            holder.phone=(TextView)v.findViewById(R.id.phonenumber);

            holder.delete=(Button)v.findViewById(R.id.delete);
            holder.cardView= (CardView)v.findViewById(R.id.card);
            // holder.uLocation = (TextView) v.findViewById(R.id.location);
            v.setTag(holder);
        } else {
            holder = (BranchAdapter.ViewHolder) v.getTag();

        }

        holder.delete.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                       context.getContentResolver().delete(

                               Uri.parse("content://com.example.eeaassignment.StudentsProvider/students/"+listData.get(position).getId()), listData.get(position).getId(), null);
                         Toast.makeText(context,"Deleted", Toast.LENGTH_LONG).show();
                        Log.d("myTag", "Item is deleted");
                    }
                }
        );

        holder.view.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         editText= new EditText(context);
                        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                        alertDialog.setTitle("Update");
                        alertDialog.setView(editText);
                        alertDialog.setMessage("Enter employee's new contact number here "  );
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        ContentValues values = new ContentValues();
                                        values.put(StudentsProvider.PHONE,
                                                editText.getText().toString());
                                        context.getContentResolver().update(

                                                Uri.parse("content://com.example.eeaassignment.StudentsProvider/students/"+listData.get(position).getId()),values, listData.get(position).getId(), null);
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



        holder.uName.setText(listData.get(position).getName());
        holder.uDesignation.setText(listData.get(position).getLocation());
        holder.phone.setText(listData.get(position).getPhoneNumber());
        return v;
    }
    static class ViewHolder {
        TextView uName;
        TextView uDesignation;
        TextView phone;
        Button view;
        Button delete;
        CardView cardView;
        ImageView img;

    }

}
