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

public class register extends AppCompatActivity {

    EditText username, password,fullname;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fullname = findViewById(R.id.name);
        username = findViewById(R.id.email);
        password = findViewById(R.id.editTextTextPassword);
        btnRegister = findViewById(R.id.submit);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(username.getText().toString()) ){
                    Toast.makeText(com.example.eeaassignment.register.this,"Username Required", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(com.example.eeaassignment.register.this," Password Required", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(fullname.getText().toString())){
                    Toast.makeText(com.example.eeaassignment.register.this," Fullname Required", Toast.LENGTH_LONG).show();
                }
                else{
                    //proceed to login
                    login();
                }

            }
        });
    }
    public void login(){
        RegisterRequest registerRequest = new RegisterRequest(fullname.getText().toString(),username.getText().toString(),password.getText().toString());
        // loginRequest.setUsername(username.getText().toString());
        // loginRequest.setPassword(password.getText().toString());


        Call<RegisterResponse> registerResponseCall = ApiClient.getUserService().userRegister(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if(response.isSuccessful()){
                    RegisterResponse registerResponse = response.body();
                    Log.d("myTag", "This is my message"+registerResponse.getMessage());
                    Toast.makeText(com.example.eeaassignment.register.this,""+registerResponse.getMessage(), Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            startActivity(new Intent(com.example.eeaassignment.register.this, com.example.eeaassignment.Login2.class).putExtra("data",registerResponse.getMessage()));
                        }
                    },700);

                }else{
                    //RegisterResponse registerResponse = response.body();
                    Toast.makeText(com.example.eeaassignment.register.this,"Registration Failed,Check your entered values again.", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(com.example.eeaassignment.register.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}