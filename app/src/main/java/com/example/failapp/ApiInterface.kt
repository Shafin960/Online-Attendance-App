package com.example.failapp

import com.example.failapp.models.Stores
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {
    @GET("/api/stores")
    fun getStores(@Query("page") page: Int): Call<Stores>
}