package com.example.eeaassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;

public class Homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
 ViewFlipper v_flipper;
 Button login;
 Button search;
 Button contactUs;
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        login=(Button)findViewById(R.id.button3);
        search=(Button)findViewById(R.id.button);
        contactUs=(Button)findViewById(R.id.contactus);
        drawer = findViewById(R.id.drawer_layout);
        int images[]={R.drawable.pharmacy1,R.drawable.pharmacy2,R.drawable.pharmacy3};
        v_flipper=findViewById(R.id.view_flipper);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity4();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();
            }
        });
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactus();
            }
        });

        for(int image:images){
            flipperImage(image);

        }

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {



        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openActivity4() {
        Intent intent=new Intent(this,AddPharmacist.class);
        startActivity(intent);
    }
    private void openActivity1() {
        Intent intent=new Intent(this,Login1.class);
        startActivity(intent);
    }
    private void contactus() {
        Intent intent=new Intent(this,ViewCart.class);
        startActivity(intent);
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