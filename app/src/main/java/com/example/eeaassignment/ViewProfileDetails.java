package com.example.eeaassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.eeaassignment.model.User;
import com.example.eeaassignment.service.ApiClient;
import com.example.eeaassignment.util.AuthenticationHandler;
import com.example.eeaassignment.util.NavBarHandler;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile_details);
        drawer = findViewById(R.id.drawer_layout);
        image=findViewById(R.id.imageView2);
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
                    Bitmap bimage=null;
                    InputStream in= null;
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    try {
                        URL url = new URL("http://192.168.1.3:8080/api/auth/video/"+viewItem.getImageName());
                        bimage  = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                        image.setImageBitmap(bimage);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(ViewProfileDetails.this, "Profile of "+viewItem.getUsername(), Toast.LENGTH_SHORT).show();
                    Log.d("myTag", "method is called");
                }
                else{

                    Toast.makeText(ViewProfileDetails.this, "Check user inputs and try again!", Toast.LENGTH_SHORT).show();

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