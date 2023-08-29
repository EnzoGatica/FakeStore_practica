package com.example.fakestore_practica.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fakestore_practica.data.remote.Rating

@Entity(tableName = "Product_table")
data class ProductoEntity(
    @PrimaryKey val id: Int,
    val titulo: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rate: Double,
    val count: Int
)