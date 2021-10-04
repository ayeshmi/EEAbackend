package com.example.eeaassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login1 extends AppCompatActivity {
Button login;
Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        login=(Button)findViewById(R.id.loginB);
        register=(Button)findViewById(R.id.signupB);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginActivity();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerActivity();
            }
        });
    }
    private void loginActivity() {
        Intent intent=new Intent(this,Login2.class);
        startActivity(intent);
    }
    private void registerActivity() {
        Intent intent=new Intent(this,register.class);
        startActivity(intent);
    }
}