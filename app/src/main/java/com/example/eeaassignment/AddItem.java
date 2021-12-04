package com.example.eeaassignment;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        itemName=findViewById(R.id.name);
        itemType=findViewById(R.id.itemType);
        category=findViewById(R.id.category);
        price=findViewById(R.id.price);
        description=findViewById(R.id.description);
        suitableFor=findViewById(R.id.suitableFor);
        howToUse=findViewById(R.id.howToUse);
        ingredients=findViewById(R.id.ingredients);
        delivery=findViewById(R.id.delivery);
        returnItem=findViewById(R.id.returnItem);
        image=findViewById(R.id.image);
        submit=findViewById(R.id.submit);

        

    }
}