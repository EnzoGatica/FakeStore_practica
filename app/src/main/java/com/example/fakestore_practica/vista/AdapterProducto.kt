package com.example.fakestore_practica.vista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.fakestore_practica.data.local.ProductoEntity
import com.example.fakestore_practica.databinding.ItemProductoBinding

class AdapterProducto: RecyclerView.Adapter<AdapterProducto.ItemProductoViewHolder>() {

    lateinit var binding: ItemProductoBinding
    private val listItemProducto = mutableListOf<ProductoEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemProductoViewHolder {
        binding = ItemProductoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemProductoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listItemProducto.size
    }

    override fun onBindViewHolder(holder: ItemProductoViewHolder, position: Int) {
        val producto = listItemProducto[position]
        holder.bind(producto)
    }

    fun setData(producto: List<ProductoEntity>){
        this.listItemProducto.clear()
        this.listItemProducto.addAll(producto)
        notifyDataSetChanged()
    }

    class ItemProductoViewHolder(val productoBinding: ItemProductoBinding): RecyclerView.ViewHolder(productoBinding.root) {

        fun bind(producto : ProductoEntity){
            val bundle = Bundle()

            productoBinding.txtNombre.text = producto.titulo
            productoBinding.imgProducto.load(producto.image)

        }
    }

}