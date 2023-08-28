package com.example.fakestore_practica.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductRetroFit {

    companion object{

        private const val BASE_URL = "https://fakestoreapi.com/"

        fun getRetroFitProduct(): ProductAPI{

            val mRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return mRetrofit.create(ProductAPI::class.java)

        }
    }

}