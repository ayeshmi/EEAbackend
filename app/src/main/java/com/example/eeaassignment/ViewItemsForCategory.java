package com.example.eeaassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eeaassignment.adapter.CategoryItemAdapter;
import com.example.eeaassignment.model.Item;
import com.example.eeaassignment.service.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewItemsForCategory extends AppCompatActivity {
    private List<Item>  items;
    private String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        category=intent.getStringExtra("data");
        setContentView(R.layout.activity_view_items_for_category);
        Call<List<Item>> getAllItemCall = ApiClient.getItemService().getSelectedCategoryItems(category);
        getAllItemCall .enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {

                items= response.body();

                if(items != null){
                    final ListView lv = (ListView) findViewById(R.id.item_list);
                    lv.setAdapter(new CategoryItemAdapter(ViewItemsForCategory.this, items));
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                            ListItem user = (ListItem) lv.getItemAtPosition(position);
                            Toast.makeText(ViewItemsForCategory.this, "Selected :" + " " + user.getName()+", "+ user.getLocation(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{

                    Toast.makeText(ViewItemsForCategory.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

                }
                // progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {

                Toast.makeText(ViewItemsForCategory.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();

            }
        });
    }
}