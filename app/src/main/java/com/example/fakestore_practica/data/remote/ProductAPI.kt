package com.example.fakestore_practica.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface ProductAPI {

    @GET("products/")
    suspend fun getDataProduct(): Response<List<Product>>

}