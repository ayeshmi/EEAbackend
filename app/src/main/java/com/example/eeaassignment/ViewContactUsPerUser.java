package com.example.eeaassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eeaassignment.adapter.ContactUsAdapter;
import com.example.eeaassignment.dto.ContactUsRequest;
import com.example.eeaassignment.model.Item;
import com.example.eeaassignment.service.ApiClient;
import com.example.eeaassignment.util.AuthenticationHandler;
import com.example.eeaassignment.util.NavBarHandler;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewContactUsPerUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    private List<ContactUsRequest> contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact_us_per_user);

        String check = AuthenticationHandler.validate(ViewContactUsPerUser.this, "ROLE_USER");

        if (check != null) {
            if (check.equals("Token expired")) return;
        }

        setContentView(R.layout.activity_view_all_contact_us);
        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences("auth_details", Context.MODE_PRIVATE);

        String token = "Bearer " + sharedPreferences.getString("token", null);
        String role = sharedPreferences.getString("role", null);
        String emails= sharedPreferences.getString("email", null);
        String userID= sharedPreferences.getString("id", null);
        Long uid=Long.parseLong(userID);


        Call<List<ContactUsRequest>> getAllcontactCall = ApiClient.getContactUsService().viewContactDetailsPerUser(uid);
        getAllcontactCall .enqueue(new Callback<List<ContactUsRequest>>() {
            @Override
            public void onResponse(Call<List<ContactUsRequest>> call, Response<List<ContactUsRequest>> response) {

                contacts = response.body();
                Log.d("myTag", "This is my message"+contacts);
                if(contacts != null){
                    final ListView lv = (ListView) findViewById(R.id.contact_list);
                    lv.setAdapter(new ContactUsAdapter(ViewContactUsPerUser.this, contacts));
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                            Item item = (Item) lv.getItemAtPosition(position);
                            Toast.makeText(ViewContactUsPerUser.this, "Selected :" + " " + item.getDelivery()+", "+ item.getDelivery(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{

                    Toast.makeText(ViewContactUsPerUser.this, "No Records!", Toast.LENGTH_SHORT).show();

                }
                // progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<ContactUsRequest>> call, Throwable t) {

                Toast.makeText(ViewContactUsPerUser.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        NavBarHandler.navBarHandler(item,ViewContactUsPerUser.this);

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
}