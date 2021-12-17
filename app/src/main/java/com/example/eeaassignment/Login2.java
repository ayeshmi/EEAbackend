package com.example.eeaassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eeaassignment.dto.LoginRequest;
import com.example.eeaassignment.dto.LoginResponse;
import com.example.eeaassignment.service.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login2 extends AppCompatActivity {

    EditText username, password;
    TextView forgetPassword;
    TextView register;
    Button btnLogin;
    private SharedPreferences sharedPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        username = findViewById(R.id.name);
        password = findViewById(R.id.editTextTextPassword2);
        btnLogin = findViewById(R.id.submit);
        register=findViewById(R.id.register);
        forgetPassword=findViewById(R.id.forgetPassword);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(com.example.eeaassignment.Login2.this,"Username / Password Required", Toast.LENGTH_LONG).show();
                }else{
                    //proceed to login
                    login();
                }

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(com.example.eeaassignment.Login2.this, Register.class).putExtra("data","Ayeshmi"));

            }
        });
    }

    public void login(){
        LoginRequest loginRequest = new LoginRequest(password.getText().toString(),username.getText().toString());
        // loginRequest.setUsername(username.getText().toString());
        // loginRequest.setPassword(password.getText().toString());


        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.isSuccessful()){
                  //  Log.d("myTag", "This is my message"+password.getText().toString());
                    Toast.makeText(com.example.eeaassignment.Login2.this,"Login Successful", Toast.LENGTH_LONG).show();
                    LoginResponse loginResponse = response.body();
                    sharedPrefs = Login2.this.getSharedPreferences("auth_details", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPrefs.edit();
                    editor.putString("token", response.body().getAccessToken());
                    editor.putString("email", response.body().getEmail());
                    editor.putString("username", response.body().getUsername());
                    editor.putString("id", response.body().getId().toString());
                    editor.putString("role", response.body().getRoles().get(0));
                    editor.apply();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            List<String> role =loginResponse.getRoles();
                            String roleName=role.get(0).toString();
                            if(roleName.equals("ROLE_ADMIN")){

                                startActivity(new Intent(com.example.eeaassignment.Login2.this, com.example.eeaassignment.AdminHomePage.class).putExtra("data",loginResponse.getEmail()));
                            }
                            else if(roleName.equals("ROLE_PHARMACIST")){

                                startActivity(new Intent(com.example.eeaassignment.Login2.this, com.example.eeaassignment.PharmacistHomepage.class).putExtra("data",loginResponse.getEmail()));

                            }else{
                                startActivity(new Intent(com.example.eeaassignment.Login2.this, com.example.eeaassignment.UserHomePage.class).putExtra("data",loginResponse.getEmail()));
                            }

                             }
                    },700);

                }else{

                    Toast.makeText(com.example.eeaassignment.Login2.this,"Login Failed,Check Username and Password Again", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(com.example.eeaassignment.Login2.this,"Error:  "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
}}


