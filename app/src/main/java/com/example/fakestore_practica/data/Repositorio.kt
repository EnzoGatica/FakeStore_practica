package com.example.fakestore_practica.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.fakestore_practica.data.local.ProductoDao
import com.example.fakestore_practica.data.local.ProductoDetalleEntity
import com.example.fakestore_practica.data.local.ProductoEntity
import com.example.fakestore_practica.data.remote.Product
import com.example.fakestore_practica.data.remote.ProductAPI
import com.example.fakestore_practica.data.remote.ProductDetail

class Repositorio(private val productAPI: ProductAPI, private val productoDao: ProductoDao) {

    //Listado producto
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


    //Detalle producto

    fun cargarDetalleID(id:Int): LiveData<ProductoDetalleEntity> = productoDao.getOneProductID(id)
    suspend fun cargarDetalleProducto(id:Int){
        try {
            val respuesta = productAPI.detailProduct(id)
            if (respuesta.isSuccessful){
                val resp = respuesta.body()
                resp?.let {
                    val productosDetalle = it.transformDetalle()
                    productoDao.insertDetailProduct(productosDetalle)
                }
            }else{
                Log.e("repositorio", respuesta.errorBody().toString())
            }
        }
        catch(exception: Exception){
            Log.e("catch", exception.toString())
        }

    }



}