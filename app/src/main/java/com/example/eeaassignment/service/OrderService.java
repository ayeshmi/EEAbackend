package com.example.eeaassignment.service;

import com.example.eeaassignment.model.Order;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderService {
    @POST("addToCartRA/{id}")
    Call<ResponseBody> addItemToCart(@Body Order order, @Path(value = "id", encoded = true) Long id);

    @GET("viewCartRA/{id}")
    Call<List<Order>> viewCartDetailsByUser(@Path(value = "id", encoded = true) Long id);

    @GET("viewOrderRA/{id}")
    Call<List<Order>> viewOrderDetailsByUser(@Path(value = "id", encoded = true) Long id);

    @DELETE("deletecartItemRA/{id}")
    Call<ResponseBody> deleteItemFromCart(@Path(value = "id", encoded = true) Long id);

    @GET("OrderCancelationRA/{id}")
    Call<ResponseBody> cancelOrder(@Path(value = "id", encoded = true) Long id);

    @GET("OrderCompletedRA/{id}")
    Call<ResponseBody> comleteOrder(@Path(value = "id", encoded = true) Long id);

    @GET("viewPendingOrdersRA/{id}")
    Call<List<Order>> viewPendingOrders(@Path(value = "id", encoded = true) Long id);

    @GET("viewCompletedOrdersRA/{id}")
    Call<List<Order>> viewCompleteOrders(@Path(value = "id", encoded = true) Long id);

    @GET("viewCancelOrdersRA/{id}")
    Call<List<Order>> viewCancelOrders(@Path(value = "id", encoded = true) Long id);

    @GET("addPaymentRA/{price}/{deliveryFee}/{totalFee}/{email}")
    Call<ResponseBody> orderConfirmation(@Path(value = "price", encoded = true) int price,@Path(value = "deliveryFee", encoded = true) int deliveryFee,@Path(value = "totalFee", encoded = true) int totalFee,@Path(value = "email", encoded = true) String email);
}
