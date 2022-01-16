package com.example.eeaassignment.service;

import com.example.eeaassignment.service.ContactUsService;
import com.example.eeaassignment.service.ItemService;
import com.example.eeaassignment.service.OrderService;
import com.example.eeaassignment.service.PharmacistService;
import com.example.eeaassignment.service.UserService;

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
                .baseUrl("http://192.168.1.3:8080/api/auth/")
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static UserService getUserService(){
        UserService userService = getRetrofit().create(UserService.class);

        return userService;
    }

    public static ItemService getItemService(){
        ItemService itemService = getRetrofit().create(ItemService.class);

        return itemService;
    }
    public static ContactUsService getContactUsService(){
        ContactUsService contactUsService = getRetrofit().create(ContactUsService.class);

        return contactUsService;
    }
    public static PharmacistService getPharmacistService(){
        PharmacistService pharmacistService = getRetrofit().create(PharmacistService.class);

        return pharmacistService;
    }
    public static OrderService getOrderService(){
        OrderService orderService = getRetrofit().create(OrderService.class);

        return orderService;
    }

}
