/*
 * *
 *  * Created by estiv on 7/07/21 04:54 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 7/07/21 04:54 PM
 *
 */

package com.alp.app.ui.main.view.fragments


import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature
import com.alp.app.data.model.DetailTopicModel
import com.alp.app.databinding.FragmentItemBinding
import com.alp.app.singleton.PreferencesSingleton
import com.alp.app.utils.Functions
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM4 = "param4"
private const val ARG_PARAM5 = "param5"

class ItemFragment : Fragment(){

    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!
    private var param1: Int? = null
    private var param2: String? = null
    private var param3: String? = null
    private var param4: Int? = null
    private var param5: Int? = null
    private lateinit var contexto: Context
    private lateinit var functions: Functions
    private var mOnButtonClickListener: OnButtonClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
            param4 = it.getInt(ARG_PARAM4)
            param5 = it.getInt(ARG_PARAM5)
        }
    }

    internal interface OnButtonClickListener {
        fun onButtonClicked(view: View?)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentItemBinding.inflate(inflater, container, false)
        functions = Functions(requireContext())
        binding.comments.setOnClickListener {
            val idUser = PreferencesSingleton.read("id_user", 0)
            val action = DetailTopicFragmentDirections.actionDetailTopicFragmentToCommentsCourseFragment(idUser!!,param1!!)
            it.findNavController().navigate(action)
        }
        binding.btnNext.setOnClickListener {
            mOnButtonClickListener?.onButtonClicked(it)
        }
        val head = "<html><head>"
        val style = "<style type='text/css'>" +
                "@import url('https://fonts.googleapis.com/css2?family=Changa:wght@200;300;400;500;600;700;800&display=swap');" +
                "body { font-family: 'Changa', sans-serif; }" +
                "</style>"
        val endHead = "</head>"
        val body = "<body>$param2</body></html>"
        val myHtmlString = head + style + endHead + body
        binding.webView.loadDataWithBaseURL(null, myHtmlString, "text/html", "UTF-8", null)
        binding.webView.settings.javaScriptEnabled = true
        binding.level.text = param3
        binding.visits.text = "" + param4
        binding.numberComments.text = "" + param5
        binding.webView.setBackgroundColor(Color.TRANSPARENT)
        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
            val nightModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
                WebSettingsCompat.setForceDark(binding.webView.settings, WebSettingsCompat.FORCE_DARK_ON)
            } else {
                WebSettingsCompat.setForceDark(binding.webView.settings, WebSettingsCompat.FORCE_DARK_OFF)
            }
        }
        return binding.root
    }

    private fun showSnackBar(text: String){
        val snackBar = Snackbar.make(requireView(), text, Snackbar.LENGTH_LONG)
        val layoutParams = LinearLayout.LayoutParams(snackBar.view.layoutParams)
        layoutParams.gravity = Gravity.TOP
        snackBar.view.setPadding(0, 10, 0, 0)
        snackBar.view.layoutParams = layoutParams
        snackBar.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
        snackBar.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contexto = context
    }

    companion object {
        @JvmStatic fun newInstance(data: DetailTopicModel) = ItemFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_PARAM1, data.id_detail_topic)
                putString(ARG_PARAM2, data.description)
                putString(ARG_PARAM3, data.level)
                putInt(ARG_PARAM4, data.visits)
                putInt(ARG_PARAM5, data.comments)
            }
        }
    }
}



