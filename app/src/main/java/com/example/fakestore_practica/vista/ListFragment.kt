package com.example.fakestore_practica.vista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.fakestore_practica.R
import com.example.fakestore_practica.databinding.FragmentListaBinding


class ListFragment : Fragment() {

    lateinit var binding: FragmentListaBinding
    private val productoVM: ProductViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListaBinding.inflate(layoutInflater,container,false)

        initAdapter()
        productoVM.getAllProductos()

        return binding.root
    }

    private fun initAdapter() {
        val adapter = AdapterProducto()
        binding.recyclerList.adapter = adapter
        productoVM.productoLiveData().observe(viewLifecycleOwner){
            adapter.setData(it)
        }
    }


}