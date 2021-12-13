package com.example.eeaassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewProfileDetails extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private EditText usernames;
    private EditText emails;
    private EditText birthdays;
    private EditText addresss;
    private User viewItem;
    private Button button;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile_details);
        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        String check = AuthenticationHandler.validate(ViewProfileDetails.this, "ROLE_USER");

        if (check != null) {
            if (check.equals("Token expired")) return;
        }



        SharedPreferences sharedPreferences = getSharedPreferences("auth_details", Context.MODE_PRIVATE);


        String email = sharedPreferences.getString("email", null);


        usernames=(EditText)findViewById(R.id.username);
        emails=(EditText)findViewById(R.id.email);
        birthdays=(EditText)findViewById(R.id.birthday);
        addresss=(EditText)findViewById(R.id.address);
        button=(Button)findViewById(R.id.update);

        Call<User> getItem = ApiClient.getUserService().getSelectedUserDetailsByEmail(email);
        getItem .enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                viewItem = response.body();

                if(viewItem != null){
                    usernames.setText(viewItem.getUsername());
                    emails.setText(viewItem.getEmail());
                    birthdays.setText(viewItem.getBirthday());
                    addresss.setText(viewItem.getAddress());
                    Toast.makeText(ViewProfileDetails.this, "Something went wrong!"+viewItem.getEmail(), Toast.LENGTH_SHORT).show();
                    Log.d("myTag", "method is called");
                }
                else{

                    Toast.makeText(ViewProfileDetails.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

                }
                // progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(ViewProfileDetails.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();

            }
        });


        Log.d("myTag", "This is my email"+email);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateActivity();
            }
        });



    }
    private void updateActivity() {
        Intent intent=new Intent(this,UpdateProfile.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        NavBarHandler.navBarHandler(item,ViewProfileDetails.this);

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