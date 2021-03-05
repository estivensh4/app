package com.alp.app.ui.inicio

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.alp.app.R
import com.alp.app.data.RespuestaInsertarDiploma
import com.alp.app.data.RespuestaInsertarProgreso
import com.alp.app.databinding.FragmentInicioCursosDetalleTemarioBinding
import com.alp.app.servicios.APIServicio
import com.alp.app.servicios.ClaseToast
import com.alp.app.servicios.Preferencias
import com.alp.app.servicios.ServicioBuilder
import io.github.kbiakov.codeview.adapters.Options
import io.github.kbiakov.codeview.highlight.ColorTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.michaelbel.bottomsheet.BottomSheet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InicioCursosDetalleTemarioFragment : Fragment() {

    private var _binding: FragmentInicioCursosDetalleTemarioBinding? = null
    private val binding get() = _binding!!
    private lateinit var id : String
    private lateinit var total : String
    private lateinit var idcurso : String
    private lateinit var contexto: Context
    private lateinit var ultimoelemento : String
    private lateinit var viewModel: InicioCursosDetalleTemarioViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentInicioCursosDetalleTemarioBinding.inflate(inflater, container, false)
        Preferencias.init(requireContext(), "preferenciasDeUsuario")
        val bundle = this.arguments
        if (bundle != null) {
            id = bundle.getString("id", "no")
            idcurso = bundle.getString("idcurso", "no")
            total = bundle.getString("total", "no")
            ultimoelemento = bundle.getString("ultimoelemento", "no")
            val nombre = bundle.getString("nombre", "no")
            val descripcion = bundle.getString("descripcion", "no")
            val tipolenguaje = bundle.getString("tipolenguaje", "no")
            val codigo = bundle.getString("codigo", "no")
            binding.tituloTemario.text = nombre
            binding.descripcionTemario.text = descripcion
            if (descripcion.isEmpty()){
                binding.fondoDescripcionTemario.visibility = View.GONE
            } else {
                binding.fondoDescripcionTemario.visibility = View.VISIBLE
            }
            if (codigo.isEmpty()){
                binding.codeView.visibility = View.GONE
            } else {
                binding.codeView.visibility = View.VISIBLE
                binding.codeView.setOptions(
                    Options.Default.get(requireContext())
                        .withLanguage(tipolenguaje)
                        .withCode(codigo)
                        .withTheme(ColorTheme.MONOKAI)
                )
            }
        }
        binding.completar.setOnClickListener {
            if (Preferencias.leer("idsonidos", false)==true){
                val mediaPlayer = MediaPlayer.create(context, R.raw.completado)
                mediaPlayer.start()
            }
            if (total==ultimoelemento){
                insertarDiploma()
                insertarProgreso()
            } else {
                insertarProgreso()
            }
        }
        return binding.root
    }

    private fun insertarDiploma() {
        binding.cargaContenido.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            val call = ServicioBuilder.buildServicio(APIServicio::class.java)
            try {
                call.insertarDiploma(Preferencias.leer("id","0").toString(), idcurso,"1").enqueue(object :
                        Callback<RespuestaInsertarDiploma> {
                    override fun onResponse(call: Call<RespuestaInsertarDiploma>, response: Response<RespuestaInsertarDiploma>) {
                        val responsex = response.body()!!
                        activity?.runOnUiThread {
                            if (responsex.respuesta == "1") {
                                binding.cargaContenido.visibility = View.GONE
                                mostrarBottomSheet()
                            } else {
                                binding.cargaContenido.visibility = View.GONE
                                ClaseToast.mostrarx(contexto, getString(R.string.texto_diploma_activo), ContextCompat.getColor(contexto, R.color.colorGrisOscuro), R.drawable.exclamacion)
                            }
                        }
                    }
                    override fun onFailure(call: Call<RespuestaInsertarDiploma>, t: Throwable) {
                        activity!!.runOnUiThread {
                            ClaseToast.mostrarx(contexto, getString(R.string.texto_error_conexion), ContextCompat.getColor(contexto, R.color.colorGrisOscuro), R.drawable.exclamacion)
                        }
                    }
                })
            } catch (e: Throwable) {
                requireActivity().runOnUiThread {
                    ClaseToast.mostrarx(contexto, getString(R.string.texto_error_grave), ContextCompat.getColor(contexto, R.color.colorGrisOscuro), R.drawable.exclamacion)
                }
            }
        }
    }

    private fun mostrarBottomSheet() {
        val bottomSheet = BottomSheet.Builder(contexto)
            .setCustomView(R.layout.ventana_bottom_sheet)
        bottomSheet.show()
    }

    private fun insertarProgreso() {
        binding.cargaContenido.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            val call = ServicioBuilder.buildServicio(APIServicio::class.java)
            try {
                call.insertarProgreso("1", id, idcurso, Preferencias.leer("id","0").toString()).enqueue(object :
                    Callback<RespuestaInsertarProgreso> {
                    override fun onResponse(call: Call<RespuestaInsertarProgreso>, response: Response<RespuestaInsertarProgreso>) {
                        val responsex = response.body()!!
                        activity?.runOnUiThread {
                            if (responsex.respuesta == "1") {
                                Handler().postDelayed({
                                    binding.cargaContenido.visibility = View.GONE
                                }, 3000)
                            } else {
                                binding.cargaContenido.visibility = View.GONE
                                ClaseToast.mostrarx(contexto, getString(R.string.texto_error_completado_anteriormente), ContextCompat.getColor(contexto, R.color.colorGrisOscuro), R.drawable.exclamacion)
                            }
                        }
                    }
                    override fun onFailure(call: Call<RespuestaInsertarProgreso>, t: Throwable) {
                        activity!!.runOnUiThread {
                            ClaseToast.mostrarx(contexto, getString(R.string.texto_error_conexion), ContextCompat.getColor(contexto, R.color.colorGrisOscuro), R.drawable.exclamacion)
                        }
                    }
                })
            } catch (e: Throwable) {
                requireActivity().runOnUiThread {
                    ClaseToast.mostrarx(contexto, getString(R.string.texto_error_grave), ContextCompat.getColor(contexto, R.color.colorGrisOscuro), R.drawable.exclamacion)
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InicioCursosDetalleTemarioViewModel::class.java)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contexto = context
    }


}