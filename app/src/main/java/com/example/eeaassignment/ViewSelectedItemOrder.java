package com.example.eeaassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eeaassignment.model.Item;
import com.example.eeaassignment.model.Order;
import com.example.eeaassignment.service.ApiClient;
import com.example.eeaassignment.util.AuthenticationHandler;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewSelectedItemOrder extends AppCompatActivity {
    private EditText cart;
    private Button addToCart;
    private String itemId;
    private long id;
    private Item viewItem;
    EditText itemName, itemType,category,price,description,suitableFor,howToUse,ingredients,delivery,returnItem,image,availability;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String check = AuthenticationHandler.validate(ViewSelectedItemOrder.this, "ROLE_USER");

        if (check != null) {
            if (check.equals("Token expired")) return;
        }

        setContentView(R.layout.activity_view_selected_item_order);

        SharedPreferences sharedPreferences = getSharedPreferences("auth_details", Context.MODE_PRIVATE);

        String token = "Bearer " + sharedPreferences.getString("token", null);
        String role = sharedPreferences.getString("role", null);
        String email= sharedPreferences.getString("email", null);
        String userID= sharedPreferences.getString("id", null);
        Long uid=Long.parseLong(userID);

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

        availability=(EditText)findViewById(R.id.availbilty);

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


                }
                else{

                    Toast.makeText(ViewSelectedItemOrder.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

                }
                // progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

                Toast.makeText(ViewSelectedItemOrder.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();

            }
        });

        cart=(EditText)findViewById(R.id.cartItem);
        addToCart=(Button)findViewById(R.id.addToCart);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("myTag", "This is my message"+viewItem.getImageName());
                Order order=new Order(viewItem.getId(),email,uid,viewItem.getPrice(),cart.getText().toString(),viewItem.getName(),viewItem.getImage(),viewItem.getImageName());
                Call<ResponseBody> addTocart = ApiClient.getOrderService().addItemToCart(order,uid);
                addTocart .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        Toast.makeText(ViewSelectedItemOrder.this, "Item is successfully added to cart", Toast.LENGTH_SHORT).show();
                        // progressDialog.dismiss();

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        Toast.makeText(ViewSelectedItemOrder.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        //progressDialog.dismiss();

                    }
                });

            }
        });
    }
}