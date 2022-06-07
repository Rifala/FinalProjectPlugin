package com.example.finalproject.WebService

import com.example.finalproject.Model.Barang
import com.example.finalproject.Model.ListResponse
import com.example.finalproject.Model.SingleResponse
import com.example.finalproject.Model.User
import retrofit2.Call
import retrofit2.http.*

interface APIEndPoint {

    @FormUrlEncoded
    @POST("auth/sign-up")
    fun register(
        @Field("name")name: String,
        @Field("username")username: String,
        @Field("email")email: String,
        @Field("password")password: String): Call<SingleResponse<User>>


    @FormUrlEncoded
    @POST("auth/sign-in")
    fun login(
        @Field("username")username: String,
        @Field("password")password: String): Call<SingleResponse<User>>


    @GET("barang")
    fun getData(): Call<ListResponse<Barang>>


    @GET("barang/{id}")
    fun getDataById(
        @Path("id")id: Int): Call<ListResponse<Barang>>


    @FormUrlEncoded
    @POST("barang")
    fun PostData(@Field("nama")nama: String,
                 @Field("kode")kode: Int): Call<SingleResponse<Barang>>


    @FormUrlEncoded
    @PUT("barang/{id}")
    fun UpdateBarang(@Path("id")id: Int,
                     @Field("nama")nama: String,
                     @Field("kode")kode: Int): Call<SingleResponse<Barang>>


    @DELETE("barang/{id}")
    fun DeleteBarang(@Path("id")id: Int): Call<SingleResponse<Barang>>

}