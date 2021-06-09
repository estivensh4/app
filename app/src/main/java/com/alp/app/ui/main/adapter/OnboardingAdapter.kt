package com.alp.app.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alp.app.R
import com.alp.app.data.model.InduccionData
import com.alp.app.databinding.TemplateOnboardingBinding
import com.bumptech.glide.Glide


class OnboardingAdapter(val context: Context, private val arrayList: ArrayList<InduccionData>) : RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItems(modelo: InduccionData, context: Context){
            val binding = TemplateOnboardingBinding.bind(itemView)
            binding.tituloInduccion.text = modelo.titulo
            binding.descripcionInduccion.text = modelo.descripcion
            Glide.with(itemView).load(modelo.imagen).into(binding.imagenInduccion)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(context).inflate(R.layout.template_onboarding, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position], context)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}