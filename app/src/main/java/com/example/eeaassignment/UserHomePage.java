package com.example.eeaassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eeaassignment.model.Item;
import com.example.eeaassignment.util.NavBarHandler;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class UserHomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    private CardView womenHealth;
    private CardView animalCare;
    private CardView firstAid;
    private CardView eyeCare;
    private CardView babyCare;
    private CardView smokeCessation;
    private CardView sexualLife;
    private CardView cosmetics;
    private CardView supportAndBandage;
    private CardView beauty;
    private CardView earCare;
    private CardView hairCare;
    private CardView feverAndPain;
    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences("auth_details", Context.MODE_PRIVATE);
        String username =sharedPreferences.getString("username", null);
        String email = sharedPreferences.getString("email", null);
        String profileImage = sharedPreferences.getString("imageName", null);

        View hView =  navigationView.inflateHeaderView(R.layout.nav_header);
        ImageView imgvw = (ImageView)hView.findViewById(R.id.profile);
        Bitmap bimage=null;
        InputStream in= null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL("http://192.168.1.3:8080/api/auth/video/"+profileImage);
            bimage  = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            imgvw.setImageBitmap(bimage);

        } catch (IOException e) {
            e.printStackTrace();
        }

        TextView emailText = (TextView)hView.findViewById(R.id.email);
        TextView usernameText = (TextView)hView.findViewById(R.id.username);
        emailText.setText(email);
        usernameText.setText(username);

        womenHealth=(CardView)findViewById(R.id.womenHealth);
        animalCare=(CardView)findViewById(R.id.animalCare);
        firstAid=(CardView)findViewById(R.id.firstAid);
        eyeCare=(CardView)findViewById(R.id.eyeCare);
        babyCare=(CardView)findViewById(R.id.babyCare);
        smokeCessation=(CardView)findViewById(R.id.smokeCessation);
        sexualLife=(CardView)findViewById(R.id.sexualLife);
        cosmetics=(CardView)findViewById(R.id.cosmetics);
        supportAndBandage=(CardView)findViewById(R.id.supportBandage);
        beauty=(CardView)findViewById(R.id.beauty);
        earCare=(CardView)findViewById(R.id.earCare);
        hairCare=(CardView)findViewById(R.id.hairCare);
        feverAndPain=(CardView)findViewById(R.id.feverAndPain);

        womenHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("myTag", "This is my message1234");

                startActivity(new Intent(com.example.eeaassignment.UserHomePage.this, com.example.eeaassignment.ViewItemsForCategory.class).putExtra("data","WomanHealth"));


            }
        });
        animalCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(com.example.eeaassignment.UserHomePage.this, com.example.eeaassignment.ViewItemsForCategory.class).putExtra("data","AnimalCare"));

            }
        });
        firstAid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(com.example.eeaassignment.UserHomePage.this, com.example.eeaassignment.ViewItemsForCategory.class).putExtra("data","FirstAid"));

            }


        });
        eyeCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(com.example.eeaassignment.UserHomePage.this, com.example.eeaassignment.ViewItemsForCategory.class).putExtra("data","EyeCare"));

            }
        });
        babyCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(com.example.eeaassignment.UserHomePage.this, com.example.eeaassignment.ViewItemsForCategory.class).putExtra("data","BabyCare"));

            }
        });
        smokeCessation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(com.example.eeaassignment.UserHomePage.this, com.example.eeaassignment.ViewItemsForCategory.class).putExtra("data","SmokeCessation"));

            }
        });
        sexualLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(com.example.eeaassignment.UserHomePage.this, com.example.eeaassignment.ViewItemsForCategory.class).putExtra("data","SexualLife"));

            }
        });
        cosmetics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(com.example.eeaassignment.UserHomePage.this, com.example.eeaassignment.ViewItemsForCategory.class).putExtra("data","Cosmetics"));

            }
        });
        supportAndBandage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(com.example.eeaassignment.UserHomePage.this, com.example.eeaassignment.ViewItemsForCategory.class).putExtra("data","SupportandBandages"));

            }
        });
        beauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(com.example.eeaassignment.UserHomePage.this, com.example.eeaassignment.ViewItemsForCategory.class).putExtra("data","Beauty"));

            }
        });
        earCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(com.example.eeaassignment.UserHomePage.this, com.example.eeaassignment.ViewItemsForCategory.class).putExtra("data","EarCare"));

            }
        });
        hairCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(com.example.eeaassignment.UserHomePage.this, com.example.eeaassignment.ViewItemsForCategory.class).putExtra("data","HareCare"));

            }
        });
        feverAndPain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(com.example.eeaassignment.UserHomePage.this, com.example.eeaassignment.ViewItemsForCategory.class).putExtra("data","Feverandpainrelief"));

            }
        });

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        NavBarHandler.navBarHandlerUser(item,UserHomePage.this);
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