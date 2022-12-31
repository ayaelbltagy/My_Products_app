package com.example.myproducts.pojo.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Products(
    var id: Int?,
    var title: String?,
    var price: Double?,
    var description: String?,
    var category: String?,
    var image: String?,
    var rating: RatingProducts
) : Parcelable
