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

import com.example.eeaassignment.model.User;
import com.example.eeaassignment.service.ApiClient;
import com.example.eeaassignment.util.AuthenticationHandler;
import com.example.eeaassignment.util.NavBarHandler;
import com.google.android.material.navigation.NavigationView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    private EditText usernames;
    private EditText emails;
    private EditText birthdays;
    private EditText addresss;
    private User viewItem;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        String check = AuthenticationHandler.validate(UpdateProfile.this, "ROLE_USER");

        if (check != null) {
            if (check.equals("Token expired")) return;
        }



        SharedPreferences sharedPreferences = getSharedPreferences("auth_details", Context.MODE_PRIVATE);


        String email = sharedPreferences.getString("email", null);
        String userID= sharedPreferences.getString("id", null);
        Long uid=Long.parseLong(userID);

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
                    Toast.makeText(UpdateProfile.this, "Something went wrong!"+viewItem.getEmail(), Toast.LENGTH_SHORT).show();
                    Log.d("myTag", "method is called");
                }
                else{

                    Toast.makeText(UpdateProfile.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

                }
                // progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(UpdateProfile.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();

            }
        });


        Log.d("myTag", "This is my email"+email);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                User user=new User(String.valueOf(usernames.getText()),String.valueOf(emails.getText()),String.valueOf(birthdays.getText()),String.valueOf(addresss.getText()));
                Call<ResponseBody> getItem = ApiClient.getUserService().updateUser(user);
                getItem .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if(response.isSuccessful()){

                            Toast.makeText(UpdateProfile.this, "Successfully updated.", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(UpdateProfile.this, ViewCart.class);
                            UpdateProfile.this.startActivity(intent);
                        }else{

                            Toast.makeText(UpdateProfile.this, "Check user inputs and try again.", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        Toast.makeText(UpdateProfile.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        //progressDialog.dismiss();

                    }
                });

            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        NavBarHandler.navBarHandler(item,UpdateProfile.this);

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