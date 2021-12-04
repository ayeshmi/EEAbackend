package com.example.eeaassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUs extends AppCompatActivity {
    EditText name, email,message;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        name = findViewById(R.id.name);
        email= findViewById(R.id.email);
        message = findViewById(R.id.message);
        submit=findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(name.getText().toString()) ){
                    Toast.makeText(com.example.eeaassignment.ContactUs.this,"Name Required", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(com.example.eeaassignment.ContactUs.this," Email Required", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(message.getText().toString())){
                    Toast.makeText(com.example.eeaassignment.ContactUs.this," Message Required", Toast.LENGTH_LONG).show();
                }
                else{
                    //proceed to login
                    contactUs();
                }

            }
        });

    }

    public void contactUs(){
        ContactUsRequest contactUsRequest = new ContactUsRequest(name.getText().toString(),email.getText().toString(),message.getText().toString());
        // loginRequest.setUsername(username.getText().toString());
        // loginRequest.setPassword(password.getText().toString());


        Call<RegisterResponse> registerResponseCall = ApiClient.getContactUsService().contactUs(contactUsRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if(response.isSuccessful()){
                    RegisterResponse registerResponse = response.body();
                    Log.d("myTag", "This is my message"+registerResponse.getMessage());
                    Toast.makeText(com.example.eeaassignment.ContactUs.this,""+registerResponse.getMessage(), Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            startActivity(new Intent(com.example.eeaassignment.ContactUs.this, com.example.eeaassignment.Login2.class).putExtra("data",registerResponse.getMessage()));
                        }
                    },700);

                }else{
                    //RegisterResponse registerResponse = response.body();
                    Toast.makeText(com.example.eeaassignment.ContactUs.this,"Submission failed,Check your entered values again.", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(com.example.eeaassignment.ContactUs.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }


}