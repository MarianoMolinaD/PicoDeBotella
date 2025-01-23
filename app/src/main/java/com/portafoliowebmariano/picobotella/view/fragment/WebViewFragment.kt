package com.portafoliowebmariano.picobotella.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.portafoliowebmariano.picobotella.R
import com.portafoliowebmariano.picobotella.databinding.FragmentWebViewBinding
import com.portafoliowebmariano.picobotella.viewmodel.PlayViewModel

class WebViewFragment : Fragment() {
    private val playViewModel: PlayViewModel by viewModels()
    private lateinit var binding: FragmentWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentWebViewBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller()
    }

    private fun controller() {
        binding.icContentBar.tvTitle.text = "Mas Apps"
        binding.icContentBar.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        assignWebView()
    }

    private fun assignWebView() {
        val urlWeb = playViewModel.getUrlApp(requireActivity())
        binding.wvContainer.apply {
            settings.apply {
                javaScriptEnabled = true
            }
            webViewClient = WebViewClient()
            loadUrl(urlWeb)
        }
    }
}