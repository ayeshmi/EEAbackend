package com.example.eeaassignment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @POST("signin/")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);
    @POST("signup/")
    Call<RegisterResponse> userRegister(@Body RegisterRequest registerRequest);
    @POST("contactus/")
    Call<RegisterResponse> contactUs(@Body ContactUsRequest contactUsRequest);
    @GET("employees12/")
    Call<List<User>> getAllUser();
}
