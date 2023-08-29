package com.example.fakestore_practica.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductoDao {

    //Listado todos producto
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProduct(productList: List<ProductoEntity>)

    @Query("SELECT * FROM product_table order by id Asc")
    fun getAllProduct(): LiveData<List<ProductoEntity>>

    //Detalle producto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailProduct(productoDetalle: ProductoDetalle)

    @Query("SELECT * FROM detail_table WHERE id = :id")
    fun getOneProductID(id:Int): LiveData<ProductoDetalle>

}