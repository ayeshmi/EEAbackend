package com.example.eeaassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.eeaassignment.util.NavBarHandler;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PharmacistHomepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    ViewFlipper v_flipper;
    String role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist_homepage);

        drawer = findViewById(R.id.drawer_layout);

        int images[]={R.drawable.pharmacy1,R.drawable.pharmacy2,R.drawable.pharmacy3};
        v_flipper=findViewById(R.id.view_flipper);

        for(int image:images){
            flipperImage(image);
        }

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences("auth_details", Context.MODE_PRIVATE);
        String username =sharedPreferences.getString("username", null);
        String email = sharedPreferences.getString("email", null);
        String profileImage = sharedPreferences.getString("imageName", null);
         role=sharedPreferences.getString("role",null);

        NavBarHandler.handleHeaderAndMenu(navigationView,username,email,profileImage, role);

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(role.equals("ROLE_ADMIN")){
            NavBarHandler.navBarHandler(item,PharmacistHomepage.this);
        }else if(role.equals("ROLE_PHARMACIST")){
            NavBarHandler.navBarHandlerPharmacist(item,PharmacistHomepage.this);
        }else{
            NavBarHandler.navBarHandlerUser(item,PharmacistHomepage.this);
        }

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
    public void flipperImage(int image){
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

}