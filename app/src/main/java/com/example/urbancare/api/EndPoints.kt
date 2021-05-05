package com.example.urbancare.api

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface EndPoints {


    @GET("api/users")
    fun getUsers(): Call<List<User>>

    @GET("api/users/{id}")
    fun getUserById(@Path("id") id: Int): Call<User>

    @FormUrlEncoded
    @POST("api/login")
    fun login(
            @Field("username") username: String?,
            @Field("password") password: String?
    ): Call<OutputLogin>

    @Multipart
    @POST("api/addReport")
    fun report(
            @Part("titulo") titulo: RequestBody?,
            @Part("descricao") descricao: RequestBody?,
            @Part("latitude") latitude: RequestBody?,
            @Part("descricao") longitude: RequestBody?,
            @Part("fotografia") fotografia: RequestBody?,
            @Part("tipo") tipo: RequestBody?
            ): Call<OutputReport>
}