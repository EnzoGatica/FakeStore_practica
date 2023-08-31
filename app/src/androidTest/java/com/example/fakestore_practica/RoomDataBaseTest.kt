package com.example.fakestore_practica

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.fakestore_practica.data.local.ProductoDao
import com.example.fakestore_practica.data.local.ProductoDataBase
import com.example.fakestore_practica.data.local.ProductoEntity
import com.example.fakestore_practica.data.remote.Rating
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class RoomDataBaseTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var productoDao: ProductoDao
    private lateinit var db: ProductoDataBase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ProductoDataBase::class.java).build()
        productoDao = db.getProductDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertProducto_empty() = runBlocking {
        // Given
        val productoList = listOf<ProductoEntity>()

        // When
        productoDao.insertAllProduct(productoList)

        // Then A
        val it = productoDao.getAllProduct().getOrAwaitValue()
        assertThat(it).isNotNull() //assertNotEquals(it, null) Son equivalentes
        assertThat(it).isEmpty() //assertEquals(it.size, 0) Son equivalentes

        // Then B
        productoDao.getAllProduct().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertProducto_happyCase_1element() = runBlocking {
        // Given
        val productoList = listOf(ProductoEntity(1,"Hola", 2.0,"Bye","Mejor Producto","example.com", 0.0,0))

        // When
        productoDao.insertAllProduct(productoList)

        // Then
        productoDao.getAllProduct().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun insertProducto_happyCase_3elements() = runBlocking {
        // Given
        val productoList = listOf(
            ProductoEntity(1,"Hola", 2.0,"Bye","Mejor Producto","example.com", 0.0,0),
            ProductoEntity(2,"Hola", 4.0,"Bye","Mejor Producto","example.com", 0.0,0),
            ProductoEntity(3,"Hola", 6.0,"Bye","Mejor Producto","example.com", 0.0,0))

        // When
        productoDao.insertAllProduct(productoList)

        // Then
        productoDao.getAllProduct().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(3)
        }
    }
}

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}