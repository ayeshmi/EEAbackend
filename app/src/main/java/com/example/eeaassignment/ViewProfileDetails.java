package com.example.eeaassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewProfileDetails extends AppCompatActivity {
    private EditText usernames;
    private EditText emails;
    private EditText birthdays;
    private EditText addresss;
    private User viewItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile_details);


        String check = AuthenticationHandler.validate(ViewProfileDetails.this, "ROLE_USER");

        if (check != null) {
            if (check.equals("Token expired")) return;
        }



        SharedPreferences sharedPreferences = getSharedPreferences("auth_details", Context.MODE_PRIVATE);


        String email = sharedPreferences.getString("email", null);


        usernames=(EditText)findViewById(R.id.username);
        emails=(EditText)findViewById(R.id.email);
        birthdays=(EditText)findViewById(R.id.birthday);
        addresss=(EditText)findViewById(R.id.address);

        Call<User> getItem = ApiClient.getUserService().getSelectedUserDetailsByEmail(email);
        getItem .enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                viewItem = response.body();

                if(viewItem != null){
                    usernames.setText(viewItem.getUsername());
                    emails.setText(viewItem.getEmail());
                    birthdays.setText(viewItem.getBirthday());
                    addresss.setText(viewItem.getAddress());
                    Toast.makeText(ViewProfileDetails.this, "Something went wrong!"+viewItem.getEmail(), Toast.LENGTH_SHORT).show();
                    Log.d("myTag", "method is called");
                }
                else{

                    Toast.makeText(ViewProfileDetails.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

                }
                // progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(ViewProfileDetails.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();

            }
        });


        Log.d("myTag", "This is my email"+email);






    }
}