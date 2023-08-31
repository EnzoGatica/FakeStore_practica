package com.example.fakestore_practica.data

import com.example.fakestore_practica.data.remote.Product
import com.example.fakestore_practica.data.remote.ProductDetail
import com.example.fakestore_practica.data.remote.Rating
import com.example.fakestore_practica.data.remote.RatingDetail
import org.junit.Assert.*

import org.junit.Test

/*
this.id,
    this.titulo,
    this.price,
    this.description,
    this.category,
    this.image,
    this.rating.rate,
    this.rating.count
 */

class MapperKtTest {

    @Test
    fun transformEntity() {

        //Given
        val unProduct = Product(1,"Hola", 2.0,"Bye","Mejor Producto","example.com", Rating(3.0,1))

        //when
        val result = unProduct.transformEntity()

        //then
        assertEquals(unProduct.id, result.id)
        assertEquals(unProduct.titulo, result.titulo)
        assertEquals(unProduct.price, result.price, 0.0)
        assertEquals(unProduct.description,result.description)
        assertEquals(unProduct.category,result.category)
        assertEquals(unProduct.image,result.image)
        assertEquals(unProduct.rating.rate,result.rate, 0.0)
        assertEquals(unProduct.rating.count,result.count)

    }

    @Test
    fun transformDetalle() {

        val unDetalle = ProductDetail(1,"Hola", 2.0,"Bye","Mejor Producto","example.com", RatingDetail(3.0,1))

        //when
        val resultDetalle = unDetalle.transformDetalle()

        //then
        assertEquals(unDetalle.id, resultDetalle.id)
        assertEquals(unDetalle.titulo, resultDetalle.titulo)
        assertEquals(unDetalle.price, resultDetalle.price, 0.0)
        assertEquals(unDetalle.description,resultDetalle.description)
        assertEquals(unDetalle.category,resultDetalle.category)
        assertEquals(unDetalle.image,resultDetalle.image)
        assertEquals(unDetalle.rating.rate,resultDetalle.rate, 0.0)
        assertEquals(unDetalle.rating.count,resultDetalle.count)

    }
}