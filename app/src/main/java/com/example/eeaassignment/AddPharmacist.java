package com.example.eeaassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eeaassignment.model.Pharmacist;
import com.example.eeaassignment.service.ApiClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPharmacist extends AppCompatActivity {
    EditText firstName, lastName,contactNumber,address,email,image;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pharmacist);

        firstName=(EditText)findViewById(R.id.firstName);
        lastName=(EditText)findViewById(R.id.lastName);
        contactNumber=(EditText)findViewById(R.id.contactNumber);
        address=(EditText)findViewById(R.id.address);
        email=(EditText)findViewById(R.id.email);
        image=(EditText)findViewById(R.id.image);
        submit=(Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(firstName.getText().toString()) || TextUtils.isEmpty(lastName.getText().toString()) || TextUtils.isEmpty(contactNumber.getText().toString()) || TextUtils.isEmpty(address.getText().toString()) || TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(com.example.eeaassignment.AddPharmacist.this,"Username / Password Required", Toast.LENGTH_LONG).show();
                }else{
                    //proceed to login
                    addPhamacist();
                }

            }
        });


    }

    private void addPhamacist() {
      Pharmacist pharmacist=new Pharmacist(firstName.getText().toString(),lastName.getText().toString(),contactNumber.getText().toString(),email.getText().toString(),address.getText().toString(),image.getText().toString());

        Call<ResponseBody> addCall = ApiClient.getPharmacistService().addPharmacist(pharmacist);
        addCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if(response.isSuccessful()){

                    Toast.makeText(com.example.eeaassignment.AddPharmacist.this,"Login Failed,Check Username and Password", Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            startActivity(new Intent(com.example.eeaassignment.AddPharmacist.this, com.example.eeaassignment.Login2.class));
                        }
                    },700);

                }else{
                    //RegisterResponse registerResponse = response.body();
                    Toast.makeText(com.example.eeaassignment.AddPharmacist.this,"Submission failed,Check your entered values again.", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(com.example.eeaassignment.AddPharmacist.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}