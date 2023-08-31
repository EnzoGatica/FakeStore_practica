package com.example.fakestore_practica.vista

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.fakestore_practica.R
import com.example.fakestore_practica.databinding.FragmentDetailBinding

private const val ARG_PARAM1 = "id"

class DetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var id: Int = 0
    private val productoVM: ProductViewModel by activityViewModels()
    lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        productoVM.getDetalleProducto(id)
        initListener()

        return binding.root
    }

    private fun initListener() {
        productoVM.detalleLiveData(id).observe(viewLifecycleOwner){
            if (it != null){
                binding.imageDogsDetail.load(it.image)
                binding.txtDescripcion.text = it.description
                binding.txtTitulo.text = it.titulo
                binding.txtPrecio.text = it.price.toString()
                val id = it.id
            }
            binding.btnCorreo!!.setOnClickListener{

                sendEmail(id)


            }
        }
    }

    private fun sendEmail(id:Int) {
        //mail cliente
        val destinatario = getString(R.string.destinatario_msn)
        val intentEmail = Intent(Intent.ACTION_SEND, Uri.parse(destinatario))
        intentEmail.type = "plain/text"
        //Donde llegan
        intentEmail.putExtra(Intent.EXTRA_EMAIL,arrayOf(destinatario))
        //Titulo Mail
        intentEmail.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.asunt,id))
        //Body Mail
        intentEmail.putExtra(Intent.EXTRA_TEXT,getString(R.string.body_msn,id))

        startActivity(Intent.createChooser(intentEmail, "Consulta producto"))

        findNavController().navigate(R.id.action_detailFragment_to_fragmentLista)
    }


}