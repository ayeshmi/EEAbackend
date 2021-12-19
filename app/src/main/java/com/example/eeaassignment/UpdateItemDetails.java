package com.example.eeaassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.eeaassignment.dto.ItemDTO;
import com.example.eeaassignment.model.Item;
import com.example.eeaassignment.service.ApiClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateItemDetails extends AppCompatActivity {
    private String itemId;
    private Long id;
    private Item viewItem;
    private ImageView imageView;
    EditText itemName, itemType,category,price,description,suitableFor,howToUse,ingredients,delivery,returnItem,image,availability;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item_details);

        Intent intent=getIntent();
        itemId=intent.getStringExtra("itemId");
        id=Long.parseLong(itemId);

        itemName=(EditText)findViewById(R.id.name);
        itemType=(EditText)findViewById(R.id.itemType);
        category=(EditText)findViewById(R.id.specification);
        price=(EditText)findViewById(R.id.price);
        description=(EditText)findViewById(R.id.description);
        suitableFor=(EditText)findViewById(R.id.suitableFor);
        howToUse=(EditText)findViewById(R.id.howToUse);
        ingredients=(EditText)findViewById(R.id.ingredients);
        delivery=(EditText)findViewById(R.id.delivery);
        returnItem=(EditText)findViewById(R.id.returnItem);
        submit=(Button) findViewById(R.id.submitItem);
        availability=(EditText)findViewById(R.id.availability);
        imageView=(ImageView)findViewById(R.id.image);
        Call<Item> getItem = ApiClient.getItemService().getSelectedItemDetails(id);
        getItem .enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {

                viewItem = response.body();

                if(viewItem != null){
                    itemName.setText(viewItem.getName());
                    itemType.setText(viewItem.getItemType());
                    category.setText(viewItem.getSpecifications());
                    price.setText(viewItem.getPrice());
                    description.setText(viewItem.getDescription());
                    suitableFor.setText(viewItem.getSuitableFor());
                    howToUse.setText(viewItem.getHowToUse());
                    ingredients.setText(viewItem.getIngredients());
                    delivery.setText(viewItem.getDelivery());
                    returnItem.setText(viewItem.getReturnItem());
                    availability.setText(viewItem.getAvailability());
                    Log.d("myTag", "image name"+viewItem.getImageName());
                    String imageURL=viewItem.getImageName();
                    Bitmap bimage=null;
                    InputStream in= null;
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    try {
                        URL url = new URL("http://192.168.1.3:8080/api/auth/video/"+imageURL);
                        bimage  = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                        imageView.setImageBitmap(bimage);
                        Toast.makeText(UpdateItemDetails.this, " "+viewItem.getName(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{

                    Toast.makeText(UpdateItemDetails.this, "No Records!", Toast.LENGTH_SHORT).show();

                }
                // progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

                Toast.makeText(UpdateItemDetails.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();

            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("myTag", "This is my message"+itemName.getText().toString());

                if(TextUtils.isEmpty(itemName.getText().toString()) || TextUtils.isEmpty(itemType.getText().toString()) || TextUtils.isEmpty(category.getText().toString())
                        || TextUtils.isEmpty(price.getText().toString()) || TextUtils.isEmpty(description.getText().toString())|| TextUtils.isEmpty(suitableFor.getText().toString())
                        || TextUtils.isEmpty(howToUse.getText().toString()) || TextUtils.isEmpty(ingredients.getText().toString()) || TextUtils.isEmpty(delivery.getText().toString())
                        || TextUtils.isEmpty(returnItem.getText().toString()) ){
                    Toast.makeText(com.example.eeaassignment.UpdateItemDetails.this,"All Inputs are Required, Check again.", Toast.LENGTH_LONG).show();
                }else{
                    //proceed to login
                    updateItem(itemName.getText().toString(),itemType.getText().toString(),category.getText().toString(),price.getText().toString(),description.getText().toString(),
                            suitableFor.getText().toString(), howToUse.getText().toString(),ingredients.getText().toString(),delivery.getText().toString(),returnItem.getText().toString(),
                            id);
                }

            }
        });

    }

    private void updateItem(String itemName, String itemType, String category, String price, String description, String suitableFor, String howToUse, String ingredients, String delivery, String returnItem,Long id) {
        ItemDTO item=new ItemDTO(itemName,itemType,category,price,description,suitableFor,howToUse,ingredients,delivery,returnItem);
        Call<ResponseBody> loginResponseCall = ApiClient.getItemService().updateItem(item,id);
        loginResponseCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if(response.isSuccessful()){
                    //  Log.d("myTag", "This is my message"+password.getText().toString());

                    ResponseBody responseBody = response.body();
                    Toast.makeText(com.example.eeaassignment.UpdateItemDetails.this,"Item is successfully updated.", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(com.example.eeaassignment.UpdateItemDetails.this, com.example.eeaassignment.ViewAllItems.class).putExtra("data","Ayeshmi"));


                }else{

                    Toast.makeText(com.example.eeaassignment.UpdateItemDetails.this,"Check inputs and try again.", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(com.example.eeaassignment.UpdateItemDetails.this,"Something went wrong! ", Toast.LENGTH_LONG).show();

            }
        });



    }

}