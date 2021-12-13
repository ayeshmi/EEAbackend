package com.example.eeaassignment;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.1.4:8080/api/auth/")
                .client(okHttpClient)
                .build();

        return retrofit;
    }


    public static com.example.eeaassignment.UserService getUserService(){
        com.example.eeaassignment.UserService userService = getRetrofit().create(com.example.eeaassignment.UserService.class);

        return userService;
    }

    public static com.example.eeaassignment.ItemService getItemService(){
        com.example.eeaassignment.ItemService itemService = getRetrofit().create(com.example.eeaassignment.ItemService.class);

        return itemService;
    }
    public static com.example.eeaassignment.ContactUsService getContactUsService(){
        com.example.eeaassignment.ContactUsService contactUsService = getRetrofit().create(com.example.eeaassignment.ContactUsService.class);

        return contactUsService;
    }
    public static com.example.eeaassignment.PharmacistService getPharmacistService(){
        com.example.eeaassignment.PharmacistService pharmacistService = getRetrofit().create(com.example.eeaassignment.PharmacistService.class);

        return pharmacistService;
    }
    public static com.example.eeaassignment.OrderService getOrderService(){
        com.example.eeaassignment.OrderService orderService = getRetrofit().create(com.example.eeaassignment.OrderService.class);

        return orderService;
    }

}
