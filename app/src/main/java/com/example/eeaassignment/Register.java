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
import android.widget.TextView;
import android.widget.Toast;

import com.example.eeaassignment.model.RegisterRequest;
import com.example.eeaassignment.model.RegisterResponse;
import com.example.eeaassignment.service.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    EditText username, password,fullname;
    Button btnRegister;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fullname = findViewById(R.id.name);
        username = findViewById(R.id.email);
        password = findViewById(R.id.editTextTextPassword);
        btnRegister = findViewById(R.id.submit);
        loginText=findViewById(R.id.loginText);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(username.getText().toString()) ){
                    Toast.makeText(Register.this,"Username Required", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(Register.this," Password Required", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(fullname.getText().toString())){
                    Toast.makeText(Register.this," Fullname Required", Toast.LENGTH_LONG).show();
                }
                else{
                    //proceed to login
                    login();
                }

            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Register.this, com.example.eeaassignment.Login2.class).putExtra("data","Ayeshmi"));

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
                    Toast.makeText(Register.this,""+registerResponse.getMessage(), Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(registerResponse.getMessage().equals("Incorrect format for email, try again.")){

                            }
                            else{
                                startActivity(new Intent(Register.this, com.example.eeaassignment.Login2.class).putExtra("data",registerResponse.getMessage()));
                            }
                            }
                    },700);

                }else{
                    //RegisterResponse registerResponse = response.body();
                    Toast.makeText(Register.this,"Registration Failed,Check your entered values again.", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(Register.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}