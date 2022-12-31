package com.example.myproducts.pojo.data.remote

import com.example.myproducts.pojo.models.Products
import retrofit2.Response
import retrofit2.http.GET

interface ServiceAPI {
    @GET("products")
    suspend fun getAPIProducts(): Response<List<Products>>


}