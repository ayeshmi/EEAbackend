package com.example.eeaassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eeaassignment.adapter.BranchAdapter;


import java.util.ArrayList;
import java.util.List;

public class PRoviders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_roviders);
    }
    public void onClickAddName(View view) {
        // Add a new student record
        ContentValues values = new ContentValues();
        values.put(StudentsProvider.NAME,
                ((EditText)findViewById(R.id.name)).getText().toString());

        values.put(StudentsProvider.ADDRESS,
                ((EditText)findViewById(R.id.address)).getText().toString());

        values.put(StudentsProvider.PHONE,
                ((EditText)findViewById(R.id.phone)).getText().toString());

        Uri uri = getContentResolver().insert(
                StudentsProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }
    public void onClickRetrieveStudents(View view) {
        // Retrieve student records
        String URL = "content://com.example.eeaassignment.StudentsProvider";

        Uri students = Uri.parse(URL);
        List<Employee> branches=new ArrayList<Employee>();
        Cursor c = managedQuery(students, null, null, null, "name");
        if (c.moveToFirst()) {
            do{
                Employee branch=new Employee(c.getString(c.getColumnIndex(StudentsProvider._ID)),c.getString(c.getColumnIndex( StudentsProvider.NAME)),c.getString(c.getColumnIndex( StudentsProvider.ADDRESS)),c.getString(c.getColumnIndex( StudentsProvider.PHONE)));
                branches.add(branch);
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(StudentsProvider._ID)) +
                                ", " +  c.getString(c.getColumnIndex( StudentsProvider.NAME)) +
                                ", " + c.getString(c.getColumnIndex( StudentsProvider.ADDRESS)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
        final ListView lv = (ListView) findViewById(R.id.branch_list);
        lv.setAdapter(new BranchAdapter(PRoviders.this, branches));
    }
}