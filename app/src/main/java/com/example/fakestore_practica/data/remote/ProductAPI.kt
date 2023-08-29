package com.example.fakestore_practica.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductAPI {

    @GET("products/")
    suspend fun getDataProduct(): Response<List<Product>>

    @GET("products/{id}")
    suspend fun detailProduct(@Path("id") id: Int): Response<ProductDetail>

}