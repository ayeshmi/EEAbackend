package com.example.eeaassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllPharmacists extends AppCompatActivity {
    private List<Pharmacist> pharmacists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_pharmacists);
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
}