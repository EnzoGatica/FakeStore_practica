package com.example.fakestore_practica.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_table")
class ProductoDetalle (
    @PrimaryKey val id: Int,
    val titulo: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rate: Double,
    val count: Int
)