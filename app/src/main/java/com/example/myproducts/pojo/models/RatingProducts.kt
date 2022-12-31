package com.example.myproducts.pojo.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RatingProducts(
   var rate  : Double?,
    var count : Int?

): Parcelable
