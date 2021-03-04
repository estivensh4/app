package com.alp.app.ui.perfil

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alp.app.R
import com.alp.app.data.RespuestaUsuarioData
import com.alp.app.databinding.FragmentPerfilBinding
import com.alp.app.servicios.APIServicio
import com.alp.app.servicios.ClaseToast
import com.alp.app.servicios.Preferencias
import com.alp.app.servicios.ServicioBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PerfilFragment : Fragment() {

    private lateinit var perfilViewModel: PerfilViewModel
    private var _binding: FragmentPerfilBinding? = null
    private val binding get() = _binding!!
    private lateinit var contexto: Context
    private lateinit var imagen:String
    private lateinit var nombres:String
    private lateinit var apellidos:String
    private lateinit var notificaciones:String
    private lateinit var correo:String
    private lateinit var clave:String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        perfilViewModel = ViewModelProvider(this).get(PerfilViewModel::class.java)
        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        perfilViewModel.text.observe(viewLifecycleOwner, {
            //binding.textNotifications.text = it
        })
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun recuperarDatos() {
        binding.cargaContenido.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            val call = ServicioBuilder.buildServicio(APIServicio::class.java)
            try {
                call.recuperarDatosUsuario(Preferencias.leer("id","0").toString()).enqueue(object : Callback<RespuestaUsuarioData> {
                    override fun onResponse(call: Call<RespuestaUsuarioData>, response: Response<RespuestaUsuarioData>) {
                        activity?.runOnUiThread {
                            val responsex = response.body()!!
                            if (responsex.respuesta == "1") {
                                if (responsex.datos.imagen.isEmpty()){
                                    Glide.with(contexto).load(R.drawable.boton_facebook_claro).signature(ObjectKey(System.currentTimeMillis())).into(binding.imagenPerfil)
                                } else {
                                    Glide.with(requireContext()).load(responsex.datos.imagen).signature(ObjectKey(System.currentTimeMillis())).into(binding.imagenPerfil)
                                }
                                val nombre = "${responsex.datos.nombres} ${responsex.datos.apellidos}"
                                binding.nombreCompleto.text = nombre
                                imagen = responsex.datos.imagen
                                nombres = responsex.datos.nombres
                                apellidos = responsex.datos.apellidos
                                notificaciones = responsex.datos.notificaciones
                                correo = responsex.datos.correo
                                clave = responsex.datos.clave
                                binding.cargaContenido.visibility = View.GONE
                            }
                        }
                    }

                    override fun onFailure(call: Call<RespuestaUsuarioData>, t: Throwable) {
                        activity!!.runOnUiThread {
                            ClaseToast.mostrarx(contexto, getString(R.string.texto_error_conexion), ContextCompat.getColor(contexto, R.color.colorGrisOscuro), R.drawable.exclamacion)
                            binding.cargaContenido.visibility = View.VISIBLE
                        }
                    }
                })
            } catch (e: Throwable) {
                requireActivity().runOnUiThread {
                    ClaseToast.mostrarx(contexto, getString(R.string.texto_error_grave), ContextCompat.getColor(contexto, R.color.colorGrisOscuro), R.drawable.exclamacion)
                    binding.cargaContenido.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contexto = context
    }

    override fun onStart() {
        recuperarDatos()
        super.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_perfil, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.configuraciones_perfil -> {
                val action = PerfilFragmentDirections.accionPerfilAPerfilConfiguracion(imagen, nombres, apellidos, notificaciones, correo, clave)
                findNavController().navigate(action)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}