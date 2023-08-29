package com.example.fakestore_practica.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.fakestore_practica.data.local.ProductoDao
import com.example.fakestore_practica.data.local.ProductoEntity
import com.example.fakestore_practica.data.remote.Product
import com.example.fakestore_practica.data.remote.ProductAPI

class Repositorio(private val productAPI: ProductAPI, private val productoDao: ProductoDao) {

    fun obtenerProductoEntity(): LiveData<List<ProductoEntity>> = productoDao.getAllProduct()

    suspend fun CargarProductos(){
        try {
            val respuesta = productAPI.getDataProduct()
            if (respuesta.isSuccessful){
                val resp = respuesta.body()
                resp?.let {
                    val productosEntity = it.map { it.transformEntity()}
                    productoDao.insertAllProduct(productosEntity)
                }
            }else{
                Log.e("repositorio", respuesta.errorBody().toString())
            }
        }
        catch(exception: Exception){
            Log.e("catch", exception.toString())
        }
    }

    private fun Product.transformEntity(): ProductoEntity = ProductoEntity(
        this.id,
        this.titulo,
        this.price,
        this.description,
        this.category,
        this.image,
        this.rating.rate,
        this.rating.count
    )

    

}