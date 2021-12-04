package com.example.eeaassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class
ViewSelectedItemDetails extends AppCompatActivity {
    private String itemId;
    private Long id;
    private Item viewItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_item_details);
        Intent intent=getIntent();
        itemId=intent.getStringExtra("itemId");
        id=Long.parseLong(itemId);

        Call<Item> getItem = ApiClient.getItemService().getSelectedItemDetails(id);
        getItem .enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {

                viewItem = response.body();

                if(viewItem != null){
                    Toast.makeText(ViewSelectedItemDetails.this, "Something went wrong!"+viewItem.getDescription(), Toast.LENGTH_SHORT).show();
                }
                else{

                    Toast.makeText(ViewSelectedItemDetails.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

                }
                // progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

                Toast.makeText(ViewSelectedItemDetails.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();

            }
        });




    }
}