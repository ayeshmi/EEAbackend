package com.example.eeaassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class viewSelectedUserDetails extends AppCompatActivity {
    private String userId;
    private Long id;
    private User viewUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_user_details);
        Intent intent=getIntent();
        userId=intent.getStringExtra("itemId");
        id=Long.parseLong(userId);

        Call<User> getUser = ApiClient.getUserService().getSelectedUserDetails(id);
        getUser .enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                viewUser = response.body();

                if(viewUser != null){
                    Toast.makeText(viewSelectedUserDetails.this, "Something went wrong!"+viewUser.getEmail(), Toast.LENGTH_SHORT).show();
                }
                else{

                    Toast.makeText(viewSelectedUserDetails.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

                }
                // progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(viewSelectedUserDetails.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();

            }
        });





    }
}