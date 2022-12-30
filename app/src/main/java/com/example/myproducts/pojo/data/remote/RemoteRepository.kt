package com.example.myproducts.pojo.data.remote

import com.example.myproducts.pojo.models.Products
import retrofit2.Response

interface RemoteRepository {
    suspend fun getAPIProducts(): Response<List<Products>>
}