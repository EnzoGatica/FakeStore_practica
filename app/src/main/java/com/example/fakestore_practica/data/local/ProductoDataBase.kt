package com.example.fakestore_practica.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductoEntity::class], version = 1)
abstract class ProductoDataBase: RoomDatabase() {

    abstract fun getProductDao(): ProductoDao

    companion object {
        //@Volatile
        private var INSTANCE: ProductoDataBase? = null

        fun getDatabase(context: Context): ProductoDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductoDataBase::class.java,
                    "producto_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}