package com.example.eeaassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eeaassignment.adapter.ItemAdapter;
import com.example.eeaassignment.adapter.UserAdapter;
import com.example.eeaassignment.model.Item;
import com.example.eeaassignment.model.User;
import com.example.eeaassignment.service.ApiClient;
import com.example.eeaassignment.util.AuthenticationHandler;
import com.example.eeaassignment.util.NavBarHandler;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllItems extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private List<Item> items;
    private Button addNewItem;
    private Button deleteButton;
    private TextView itemName;
    private DrawerLayout drawer;
    private Button button;
    private EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_items);
        drawer = findViewById(R.id.drawer_layout);
        button=findViewById(R.id.confirm2);
        search=findViewById(R.id.name6);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(search.getText().toString()) ){
                    Toast toast =  Toast.makeText(ViewAllItems.this,"Input Required", Toast.LENGTH_LONG);
                    toast.show();


                }else{
                    //proceed to login
                    search(search.getText().toString());
                }

            }
        });

        String check = AuthenticationHandler.validate(ViewAllItems.this, "Admin");

        if (check != null) {
            if (check.equals("Token expired")) return;
        }

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        SharedPreferences sharedPreferences = getSharedPreferences("auth_details", Context.MODE_PRIVATE);

        String token = "Bearer " + sharedPreferences.getString("token", null);
        String role = sharedPreferences.getString("role", null);


        Call<List<Item>> getAllitemCall = ApiClient.getItemService().getAllItems();
        getAllitemCall .enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {

                items = response.body();

                if(items.size() != 0){
                    final ListView lv = (ListView) findViewById(R.id.item_list);
                    lv.setAdapter(new ItemAdapter(ViewAllItems.this, items));
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                            Item item = (Item) lv.getItemAtPosition(position);
                            Toast.makeText(ViewAllItems.this, "View All Items", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{

                    Toast.makeText(ViewAllItems.this, "No Records!", Toast.LENGTH_SHORT).show();

                }
                // progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {

                Toast.makeText(ViewAllItems.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();

            }
        });
        //ArrayList userList = results;

    }

    private void addNewItem() {
        Intent intent=new Intent(this,AddItem.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        NavBarHandler.navBarHandler(item,ViewAllItems.this);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void search(String seachWord){
        Call<List<Item>> getAllitemCall = ApiClient.getItemService().advanceItemSearch(seachWord);
        getAllitemCall .enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {

                items = response.body();

                if(items.size() != 0){
                    final ListView lv = (ListView) findViewById(R.id.item_list);
                    lv.setAdapter(new ItemAdapter(ViewAllItems.this, items));
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                            Item item = (Item) lv.getItemAtPosition(position);
                            Toast.makeText(ViewAllItems.this, "View All Items", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{

                    Toast.makeText(ViewAllItems.this, "No Records found!", Toast.LENGTH_SHORT).show();

                }
                // progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {

                Toast.makeText(ViewAllItems.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();

            }
        });

    }

}