package com.example.eeaassignment;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ContactUsService {
    @POST("contactusRA/")
    Call<RegisterResponse> contactUs(@Body ContactUsRequest contactUsRequest);
}
