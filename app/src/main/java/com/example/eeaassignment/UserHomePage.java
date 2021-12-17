package com.example.eeaassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.eeaassignment.model.Item;
import com.example.eeaassignment.util.NavBarHandler;
import com.google.android.material.navigation.NavigationView;

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