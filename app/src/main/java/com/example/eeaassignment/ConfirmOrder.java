package com.example.eeaassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.eeaassignment.model.RegisterResponse;
import com.example.eeaassignment.service.ApiClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmOrder extends AppCompatActivity {
    private String totalPrice;
    private Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        confirm=findViewById(R.id.confirm);

        SharedPreferences sharedPreferences = getSharedPreferences("auth_details", Context.MODE_PRIVATE);
        String email= sharedPreferences.getString("email", null);

        Intent intent=getIntent();
        totalPrice=intent.getStringExtra("date");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseBody> registerResponseCall = ApiClient.getOrderService().orderConfirmation(1500,250,1500,email);
                registerResponseCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if(response.isSuccessful()){

                            Toast.makeText(ConfirmOrder.this, "Order Payment is successfully completed.", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(ConfirmOrder.this, ViewCart.class);
                            ConfirmOrder.this.startActivity(intent);
                        }else{

                            Toast.makeText(ConfirmOrder.this, "Something went wrong, try again.", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(ConfirmOrder.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                    }
                });
            }
        });


    }
}