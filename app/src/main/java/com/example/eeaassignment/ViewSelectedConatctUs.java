package com.example.eeaassignment;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eeaassignment.dto.ContactUsRequest;
import com.example.eeaassignment.service.ApiClient;
import com.example.eeaassignment.util.NavBarHandler;
import com.google.android.material.navigation.NavigationView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewSelectedConatctUs extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private String conatctId;
    private Long id;
    private ContactUsRequest viewItem;
    private EditText name;
    private EditText email;
    private EditText message;
    private EditText answer;
    private Button submit;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_conatct_us);
        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Intent intent = getIntent();
        conatctId = intent.getStringExtra("itemId");
        id = Long.parseLong(conatctId);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        message = (EditText) findViewById(R.id.message);
        answer = (EditText) findViewById(R.id.answer);
        submit = (Button) findViewById(R.id.submit);

        Call<ContactUsRequest> getItem = ApiClient.getContactUsService().getSelectedContactDetails(id);
        getItem .enqueue(new Callback<ContactUsRequest>() {
            @Override
            public void onResponse(Call<ContactUsRequest> call, Response<ContactUsRequest> response) {

                viewItem = response.body();

                if(viewItem != null){
                    name.setText(viewItem.getName());
                    email.setText(viewItem.getEmail());
                    message.setText(viewItem.getMessage());
                    answer.setText(viewItem.getAnswer());
                    Toast.makeText(ViewSelectedConatctUs.this, "View ContactUs Details!", Toast.LENGTH_SHORT).show();
                    Log.d("myTag", "method is called");
                }
                else{

                    Toast.makeText(ViewSelectedConatctUs.this, "No Record Found!", Toast.LENGTH_SHORT).show();

                }
                // progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ContactUsRequest> call, Throwable t) {

                Toast.makeText(ViewSelectedConatctUs.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(answer.getText().toString())){
                    Toast.makeText(com.example.eeaassignment.ViewSelectedConatctUs.this,"Answer is required!", Toast.LENGTH_LONG).show();
                }else{
                    viewItem.setAnswer(answer.getText().toString());
                    //proceed to login
                    replyContactUs(viewItem);
                }

            }
        });
    }



   public void replyContactUs(ContactUsRequest contactUs){
      // LoginRequest loginRequest = new LoginRequest(answer.getText().toString(),username.getText().toString());
       // loginRequest.setUsername(username.getText().toString());
       // loginRequest.setPassword(password.getText().toString());


       Call<ResponseBody> loginResponseCall = ApiClient.getContactUsService().replyConatctUs(contactUs.getId(),contactUs);
       loginResponseCall.enqueue(new Callback<ResponseBody>() {
           @Override
           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

               if(response.isSuccessful()){
                   //  Log.d("myTag", "This is my message"+password.getText().toString());
                   Toast.makeText(com.example.eeaassignment.ViewSelectedConatctUs.this,"Your answer is sent to the client!", Toast.LENGTH_LONG).show();




               }else{

                   Toast.makeText(com.example.eeaassignment.ViewSelectedConatctUs.this,"Check user inputs and try again!", Toast.LENGTH_LONG).show();

               }

           }

           @Override
           public void onFailure(Call<ResponseBody> call, Throwable t) {
               Toast.makeText(com.example.eeaassignment.ViewSelectedConatctUs.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

           }
       });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        NavBarHandler.navBarHandler(item,ViewSelectedConatctUs.this);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}