package com.example.eeaassignment;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @POST("signin/")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);
    @POST("signup/")
    Call<RegisterResponse> userRegister(@Body RegisterRequest registerRequest);

    @GET("employees12/")
    Call<List<User>> getAllUser();
    @GET("viewUserByIDRA/{id}")
    Call<User> getSelectedUserDetails(@Path(value = "id", encoded = true) Long id);
    @DELETE("deleteUser/{userId}")
    Call<ResponseBody> deleteUser(@Path(value = "userId", encoded = true) Long id);
}
