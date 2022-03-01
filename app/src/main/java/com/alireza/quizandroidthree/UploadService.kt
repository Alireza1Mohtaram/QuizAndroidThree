package com.alireza.hw13

import com.alireza.quizandroidthree.User
import com.alireza.quizandroidthree.UserXItem
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface UploadService {

    @Multipart
    @POST("users/{id}/")
    fun uploadProfile(
        @Path("id") id :String ,
        @Part image:MultipartBody.Part
    ):Call<String>

    @Headers("Authorization: Bearer asdfasdf")
    @POST("users")
    fun registerUser(@Body user:User
    ):Call<String>


    @GET("users")
    fun getusers():Call<List<UserXItem>>
}