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

public interface ContactUsService {
    @POST("contactusRA/")
    Call<RegisterResponse> contactUs(@Body ContactUsRequest contactUsRequest);
    @GET("allConatctUsRA/")
    Call<List<ContactUsRequest>> getAllConatctUs();
    @DELETE("deleteContactUsRA/{Id}")
    Call<ResponseBody> deleteContectUs(@Path(value = "Id", encoded = true) Long id);
    @GET("contactusDetailsRA/{id}")
    Call<ContactUsRequest> getSelectedContactDetails(@Path(value = "id", encoded = true) Long id);
    @PUT("contactusReplyRA/{id}")
    Call<ResponseBody> replyConatctUs(@Path(value = "id", encoded = true) Long id,@Body ContactUsRequest contactUsRequest);
}
