package com.example.eeaassignment;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddItem extends AppCompatActivity {
    EditText itemName, itemType,category,price,description,suitableFor,howToUse,ingredients,delivery,returnItem,image;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String check = AuthenticationHandler.validate(AddItem.this, "Admin");

        if (check != null) {
            if (check.equals("Token expired")) return;
        }

        setContentView(R.layout.activity_add_item);

        SharedPreferences sharedPreferences = getSharedPreferences("auth_details", Context.MODE_PRIVATE);

        String token = "Bearer " + sharedPreferences.getString("token", null);
        String role = sharedPreferences.getString("role", null);

        itemName=(EditText)findViewById(R.id.itemName);
        itemType=(EditText)findViewById(R.id.itemType);
        category=(EditText)findViewById(R.id.category);
        price=(EditText)findViewById(R.id.price);
        description=(EditText)findViewById(R.id.description);
        suitableFor=(EditText)findViewById(R.id.suitableFor);
        howToUse=(EditText)findViewById(R.id.howToUse);
        ingredients=(EditText)findViewById(R.id.ingredients);
        delivery=(EditText)findViewById(R.id.delivery);
        returnItem=(EditText)findViewById(R.id.returnItem);
        image=(EditText)findViewById(R.id.image);
        submit=(Button) findViewById(R.id.submitItem);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("myTag", "This is my message"+itemName.getText().toString());

                if(TextUtils.isEmpty(itemName.getText().toString()) || TextUtils.isEmpty(itemType.getText().toString()) || TextUtils.isEmpty(category.getText().toString())
                        || TextUtils.isEmpty(price.getText().toString()) || TextUtils.isEmpty(description.getText().toString())|| TextUtils.isEmpty(suitableFor.getText().toString())
                        || TextUtils.isEmpty(howToUse.getText().toString()) || TextUtils.isEmpty(ingredients.getText().toString()) || TextUtils.isEmpty(delivery.getText().toString())
                        || TextUtils.isEmpty(returnItem.getText().toString()) || TextUtils.isEmpty(image.getText().toString())){
                    Toast.makeText(com.example.eeaassignment.AddItem.this,"All Inputs are Required, Check again.", Toast.LENGTH_LONG).show();
                }else{
                    //proceed to login
                    addItem(itemName.getText().toString(),itemType.getText().toString(),category.getText().toString(),price.getText().toString(),description.getText().toString(),
                            suitableFor.getText().toString(), howToUse.getText().toString(),ingredients.getText().toString(),delivery.getText().toString(),returnItem.getText().toString(),
                            image.getText().toString());
                }

            }
        });

        

    }

    private void addItem(String itemName, String itemType, String category, String price, String description, String suitableFor, String howToUse, String ingredients, String delivery, String returnItem, String image) {
        ItemDTO item=new ItemDTO(itemName,itemType,category,price,description,suitableFor,howToUse,ingredients,delivery,returnItem);
        Call<ResponseBody> loginResponseCall = ApiClient.getItemService().addItem(item);
        loginResponseCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if(response.isSuccessful()){
                    //  Log.d("myTag", "This is my message"+password.getText().toString());

                    ResponseBody responseBody = response.body();
                    Toast.makeText(com.example.eeaassignment.AddItem.this,"Item is succesfully added", Toast.LENGTH_LONG).show();

                }else{

                    Toast.makeText(com.example.eeaassignment.AddItem.this,"Login Failed,Check Username and Password", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(com.example.eeaassignment.AddItem.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });



    }


}