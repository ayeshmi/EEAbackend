package com.example.eeaassignment;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PharmacistService {

    @POST("addPharmacientRA/")
    Call<ResponseBody> addPharmacist(@Body Pharmacist pharmacist);
    @GET("viewAllPharmacientRA/")
    Call<List<Pharmacist>> getAllPharmacist();
    @DELETE("deletePharmacientRA/{Id}")
    Call<ResponseBody> deletePharmacist(@Path(value = "Id", encoded = true) Long id);
    @GET("viewPharmacientByItemRA/{id}")
    Call<Pharmacist> getSelectedPharmacistDetails(@Path(value = "id", encoded = true) Long id);

}
