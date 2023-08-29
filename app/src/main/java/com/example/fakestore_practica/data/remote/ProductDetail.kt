package com.example.fakestore_practica.data.remote

import com.google.gson.annotations.SerializedName

data class ProductDetail(
        val id: Int,
        @SerializedName("title") val titulo: String,
        val price: Double,
        val description: String,
        val category: String,
        val image: String,
        val rating: RatingDetail
    )

    data class RatingDetail(
        val rate: Double,
        val count: Int
    )