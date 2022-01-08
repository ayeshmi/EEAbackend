package com.example.eeaassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.eeaassignment.model.Pharmacist;
import com.example.eeaassignment.service.ApiClient;
import com.example.eeaassignment.util.NavBarHandler;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewSelctedPharmacist extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private String conatctId;
    private Long id;
    private Pharmacist viewItem;
    private EditText name;
    private EditText email;
    private EditText message;
    private EditText answer;
    private Button submit;
    private ImageView imageView;
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selcted_pharmacist);
        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent=getIntent();
        conatctId=intent.getStringExtra("itemId");
        id=Long.parseLong(conatctId);

        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        message=(EditText)findViewById(R.id.message);
        answer=(EditText)findViewById(R.id.answer);
        submit=(Button) findViewById(R.id.submit);
        imageView=(ImageView) findViewById(R.id.img);


        Call<Pharmacist> getItem = ApiClient.getPharmacistService().getSelectedPharmacistDetails(id);
        getItem .enqueue(new Callback<Pharmacist>() {
            @Override
            public void onResponse(Call<Pharmacist> call, Response<Pharmacist> response) {

                viewItem = response.body();

                if(viewItem != null){
                    name.setText(viewItem.getFirstName()+" "+viewItem.getLastName());
                    email.setText(viewItem.getEmail());
                    message.setText(viewItem.getAddress());
                    answer.setText(viewItem.getContactNumber());
                    String imageURL=viewItem.getImageName();
                    Bitmap bimage=null;
                    InputStream in= null;
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    try {
                        URL url = new URL("http://192.168.1.4:8080/api/auth/video/"+imageURL);
                        bimage  = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                        imageView.setImageBitmap(bimage);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(ViewSelctedPharmacist.this, "Something went wrong!"+viewItem.getEmail(), Toast.LENGTH_SHORT).show();
                    Log.d("myTag", "method is called");
                }
                else{

                    Toast.makeText(ViewSelctedPharmacist.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

                }
                // progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<Pharmacist> call, Throwable t) {

                Toast.makeText(ViewSelctedPharmacist.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();

            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        NavBarHandler.navBarHandler(item,ViewSelctedPharmacist.this);

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