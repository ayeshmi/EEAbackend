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
                .baseUrl("http://192.168.1.3:8080/api/auth/")
                .client(okHttpClient)
                .build();

        return retrofit;
    }


    public static com.example.eeaassignment.UserService getUserService(){
        com.example.eeaassignment.UserService userService = getRetrofit().create(com.example.eeaassignment.UserService.class);

        return userService;
    }

}
