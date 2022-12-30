package com.example.myproducts.pojo.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitBuilder {

    companion object{
        const val baseUrl :String = "https://fakestoreapi.com/"

        fun getRetrofitBuilder() : Retrofit{
            return Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }
}