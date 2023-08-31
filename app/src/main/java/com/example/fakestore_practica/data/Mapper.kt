package com.example.fakestore_practica.data

import com.example.fakestore_practica.data.local.ProductoDetalleEntity
import com.example.fakestore_practica.data.local.ProductoEntity
import com.example.fakestore_practica.data.remote.Product
import com.example.fakestore_practica.data.remote.ProductDetail

fun Product.transformEntity(): ProductoEntity = ProductoEntity(
    this.id,
    this.titulo,
    this.price,
    this.description,
    this.category,
    this.image,
    this.rating.rate,
    this.rating.count
)

fun ProductDetail.transformDetalle(): ProductoDetalleEntity = ProductoDetalleEntity(
    this.id,
    this.titulo,
    this.price,
    this.description,
    this.category,
    this.image,
    this.rating.rate,
    this.rating.count
)