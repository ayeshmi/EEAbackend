package com.example.eeaassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        Call<Item> getItem = ApiClient.getItemService().getSelectedItemDetails(id);
        getItem .enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {

                viewItem = response.body();

                if(viewItem != null){
                    Toast.makeText(ViewSelectedItemOrder.this, "Something went wrong!"+viewItem.getDescription(), Toast.LENGTH_SHORT).show();
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

                Order order=new Order(viewItem.getId(),email,uid,viewItem.getPrice(),cart.getText().toString(),viewItem.getName());
                Call<ResponseBody> addTocart = ApiClient.getOrderService().addItemToCart(order);
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