package com.example.eeaassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eeaassignment.model.User;
import com.example.eeaassignment.service.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewSelectedUserDetails extends AppCompatActivity {
    private String userId;
    private Long id;
    private User viewUser;
    private EditText username;
    private EditText email;
    private EditText birthday;
    private EditText address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_user_details);
        Intent intent=getIntent();
        userId=intent.getStringExtra("itemId");
        id=Long.parseLong(userId);
        username=(EditText)findViewById(R.id.username);
        email=(EditText)findViewById(R.id.email);
        birthday=(EditText)findViewById(R.id.birthday);
        address=(EditText)findViewById(R.id.address);
        Call<User> getUser = ApiClient.getUserService().getSelectedUserDetails(id);
        getUser .enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                viewUser = response.body();

                if(viewUser != null){
                    username.setText(viewUser.getUsername());
                    email.setText(viewUser.getEmail());

                    if(viewUser.getBirthday().equals(null)){
                        birthday.setText("Not Filled");

                    }else if(viewUser.getAddress().equals(null)){
                        address.setText("Not Filled");
                    }
                    else{
                        birthday.setText(viewUser.getBirthday());
                        address.setText(viewUser.getAddress());
                    }
                    Toast.makeText(ViewSelectedUserDetails.this, "View "+viewUser.getUsername(), Toast.LENGTH_SHORT).show();
                }
                else{

                    Toast.makeText(ViewSelectedUserDetails.this, "No Record!", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(ViewSelectedUserDetails.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();

            }
        });

    }
}