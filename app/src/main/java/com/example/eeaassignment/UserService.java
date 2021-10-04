package com.example.eeaassignment;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("signin/")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);
}
