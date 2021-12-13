package com.example.eeaassignment;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OrderService {
    @POST("addToCartRA/")
    Call<ResponseBody> addItemToCart(@Body Order order);
}
