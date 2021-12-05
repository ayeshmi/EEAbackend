package com.example.eeaassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewSelctedPharmacist extends AppCompatActivity {
    private String conatctId;
    private Long id;
    private Pharmacist viewItem;
    private EditText name;
    private EditText email;
    private EditText message;
    private EditText answer;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selcted_pharmacist);

        Intent intent=getIntent();
        conatctId=intent.getStringExtra("itemId");
        id=Long.parseLong(conatctId);
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        message=(EditText)findViewById(R.id.message);
        answer=(EditText)findViewById(R.id.answer);
        submit=(Button) findViewById(R.id.submit);


        Call<Pharmacist> getItem = ApiClient.getPharmacistService().getSelectedPharmacistDetails(id);
        getItem .enqueue(new Callback<Pharmacist>() {
            @Override
            public void onResponse(Call<Pharmacist> call, Response<Pharmacist> response) {

                viewItem = response.body();

                if(viewItem != null){
                    name.setText(viewItem.getLastName());
                    email.setText(viewItem.getEmail());
                    message.setText(viewItem.getFirstName());
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

}