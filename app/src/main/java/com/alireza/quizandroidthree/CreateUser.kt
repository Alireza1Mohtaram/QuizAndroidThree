package com.alireza.quizandroidthree

import com.alireza.hw13.UploadService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CreateUser {

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("http://papp.ir/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(UploadService::class.java)
}
//
//class TokenHeader : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//
//        val req = chain.request().newBuilder()
//            .addHeader("Content-Type","application/json")
//            .addHeader("Authorization", "Bearer aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaassssssssssssssssssssssssssdddddddddddddddddddBearer Bearer B")
//            .build()
//        return chain.proceed(req)
//
//    }

