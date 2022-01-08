package com.example.eeaassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eeaassignment.model.Item;
import com.example.eeaassignment.model.Order;
import com.example.eeaassignment.service.ApiClient;
import com.example.eeaassignment.util.AuthenticationHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
    private TextView itemName;
    private ImageView image;
    private ImageButton pluse,minius;
    private Button share;
    TextView  itemType,category,price,description,suitableFor,howToUse,ingredients,delivery,returnItem,availability;
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

        itemName=(TextView)findViewById(R.id.name);
        itemType=(TextView)findViewById(R.id.itemType);
        category=(TextView)findViewById(R.id.specification);
        price=(TextView)findViewById(R.id.price);
        description=(TextView)findViewById(R.id.description);
        suitableFor=(TextView)findViewById(R.id.suitableFor);
        howToUse=(TextView)findViewById(R.id.howToUse);
        ingredients=(TextView)findViewById(R.id.ingredients);
        delivery=(TextView)findViewById(R.id.delivery);
        returnItem=(TextView)findViewById(R.id.returnItem);
        image=(ImageView)findViewById(R.id.image);
        pluse=(ImageButton) findViewById(R.id.pluse);
        minius=(ImageButton)findViewById(R.id.minius);
        share=(Button)findViewById(R.id.share);
        availability=(TextView)findViewById(R.id.availbilty);

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
                    Bitmap bimage=null;
                    InputStream in= null;
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    try {
                        URL url = new URL("http://192.168.1.3:8080/api/auth/video/"+viewItem.getImageName());
                        bimage  = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                        image.setImageBitmap(bimage);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

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

        pluse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int total=Integer.valueOf (cart.getText().toString());
                int total12=total+1;
                cart.setText(""+total12);
            }
        });
        minius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int total=Integer.valueOf (cart.getText().toString());
                if(total==0){
                    cart.setText("" +0);
                }
                else {
                    int total12=total-1;
                    cart.setText(""+total12);
                }

            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String price = viewItem.getPrice();
                String description=viewItem.getDescription();
                String howToUse=viewItem.getHowToUse();
                String category=viewItem.getSpecifications();

                String sub = viewItem.getName();
                myIntent.putExtra(Intent.EXTRA_SUBJECT,sub);
                myIntent.putExtra(Intent.EXTRA_TEXT,"Price :"+price+", Item Category :"+category+", Item Description :"+description+", How To Use :"+howToUse);

                startActivity(Intent.createChooser(myIntent, "Share Using"));
            }
        });
    }
}