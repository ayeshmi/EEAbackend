package com.example.eeaassignment;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ItemService {
    @POST("addItemRA/")
    Call<ResponseBody> addItem(@Body ItemDTO item);
    @GET("viewAllItemRA/")
    Call<List<Item>> getAllItems();
    @DELETE("deleteItemRA/{itemId}")
    Call<ResponseBody> deleteItem(@Path(value = "itemId", encoded = true) Long id);
    @GET("viewItemUpdateByItemRA/{id}")
    Call<Item> getSelectedItemDetails(@Path(value = "id", encoded = true) Long id);
    @PUT("updateItem/{id}")
    Call<ResponseBody> updateItem(@Body ItemDTO item,@Path(value = "id", encoded = true) Long id);
}
