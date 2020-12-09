package com.example.refrofitdialabar.Api_interfaces;
import com.example.refrofitdialabar.Models.register;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {
    @GET("register")
    Call<List<register>> getPosts();

    @POST("register")
    Call<register> createPost(@Body register reg);
}
