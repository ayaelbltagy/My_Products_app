package com.example.myproducts.pojo.models

import com.google.gson.annotations.SerializedName

data class RatingProducts(
    @SerializedName("rate"  ) var rate  : Double?,
    @SerializedName("count" ) var count : Int?

)
