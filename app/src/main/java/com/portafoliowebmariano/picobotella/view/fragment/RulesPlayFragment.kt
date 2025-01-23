package com.portafoliowebmariano.picobotella.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.portafoliowebmariano.picobotella.R
import com.portafoliowebmariano.picobotella.databinding.FragmentRulesPlayBinding

class RulesPlayFragment : Fragment() {
    private lateinit var binding : FragmentRulesPlayBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this
        binding = FragmentRulesPlayBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller()
    }

    private fun controller() {
        binding.icContentBar.tvTitle.text = getString(R.string.rules)
        binding.icContentBar.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}