package com.example.eeaassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eeaassignment.adapter.PharmacistAdapter;
import com.example.eeaassignment.adapter.UserAdapter;
import com.example.eeaassignment.model.Pharmacist;
import com.example.eeaassignment.model.User;
import com.example.eeaassignment.service.ApiClient;
import com.example.eeaassignment.util.NavBarHandler;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllPharmacists extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private List<Pharmacist> pharmacists;
    private DrawerLayout drawer;
    private Button button;
    private EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_pharmacists);
        drawer = findViewById(R.id.drawer_layout);
        button=findViewById(R.id.confirm2);
        search=findViewById(R.id.name6);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(search.getText().toString()) ){
                    Toast toast =  Toast.makeText(ViewAllPharmacists.this,"Input Required", Toast.LENGTH_LONG);
                    toast.show();


                }else{
                    //proceed to login
                    search(search.getText().toString());
                }

            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Call<List<Pharmacist>> getAllPharmacientsCall = ApiClient.getPharmacistService().getAllPharmacist();
        getAllPharmacientsCall .enqueue(new Callback<List<Pharmacist>>() {
            @Override
            public void onResponse(Call<List<Pharmacist>> call, Response<List<Pharmacist>> response) {

                pharmacists = response.body();

                if(pharmacists != null){
                    final ListView lv = (ListView) findViewById(R.id.pharmacists_list);
                    lv.setAdapter(new PharmacistAdapter(ViewAllPharmacists.this, pharmacists));
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                            ListItem user = (ListItem) lv.getItemAtPosition(position);
                            Toast.makeText(ViewAllPharmacists.this, "Selected :" + " " + user.getName()+", "+ user.getLocation(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{

                    Toast.makeText(ViewAllPharmacists.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

                }
                // progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<Pharmacist>> call, Throwable t) {

                Toast.makeText(ViewAllPharmacists.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        NavBarHandler.navBarHandler(item,ViewAllPharmacists.this);

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
        Call<List<Pharmacist>> getAllPharmacientsCall = ApiClient.getPharmacistService().advancePharmacistSearch(seachWord);
        getAllPharmacientsCall .enqueue(new Callback<List<Pharmacist>>() {
            @Override
            public void onResponse(Call<List<Pharmacist>> call, Response<List<Pharmacist>> response) {

                pharmacists = response.body();

                if(pharmacists != null){
                    final ListView lv = (ListView) findViewById(R.id.pharmacists_list);
                    lv.setAdapter(new PharmacistAdapter(ViewAllPharmacists.this, pharmacists));
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                            ListItem user = (ListItem) lv.getItemAtPosition(position);
                            Toast.makeText(ViewAllPharmacists.this, "Selected :" + " " + user.getName()+", "+ user.getLocation(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{

                    Toast.makeText(ViewAllPharmacists.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

                }
                // progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<Pharmacist>> call, Throwable t) {

                Toast.makeText(ViewAllPharmacists.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();

            }
        });
    }

}