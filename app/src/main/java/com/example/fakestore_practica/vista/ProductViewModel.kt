package com.example.fakestore_practica.vista

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestore_practica.data.Repositorio
import com.example.fakestore_practica.data.local.ProductoDataBase
import com.example.fakestore_practica.data.remote.ProductRetroFit
import kotlinx.coroutines.launch

class ProductViewModel(application: Application): AndroidViewModel(application) {

    private lateinit var repositorio: Repositorio


    init {
        val api = ProductRetroFit.getRetroFitProduct()
        val productoDataBase = ProductoDataBase.getDatabase(application).getProductDao()
        repositorio = Repositorio(api,productoDataBase)
    }

    fun productoLiveData() = repositorio.obtenerProductoEntity()

    fun getAllProductos() = viewModelScope.launch {
        repositorio.CargarProductos()
    }

}