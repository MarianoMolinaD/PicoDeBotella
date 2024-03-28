package com.portafoliowebmariano.picobotella.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.portafoliowebmariano.picobotella.R
import com.portafoliowebmariano.picobotella.databinding.FragmentAddChallengeBinding
import com.portafoliowebmariano.picobotella.view.adapter.ChallengeAdapter
import com.portafoliowebmariano.picobotella.view.dialog.DialogAddChallenge.showDialogAddChallenge
import com.portafoliowebmariano.picobotella.viewmodel.PlayViewModel


class AddChallengeFragment : Fragment() {
    private lateinit var binding: FragmentAddChallengeBinding
    private val playViewModel: PlayViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentAddChallengeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contoller()
        observerViewModel()
    }

    private fun contoller() {
        binding.icContentBar.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.afaButton.setOnClickListener {
            showDialogAddChallenge(requireContext(), playViewModel) {
                observerListChallelnge()
            }
        }
    }

    private fun observerViewModel() {
        observerListChallelnge()
    }

    private fun observerListChallelnge() {
        playViewModel.getListChallenge()
        playViewModel.listChallenge.observe(viewLifecycleOwner) { list ->

            val recycler = binding.rvContainerChallenge
            val layoutManager = LinearLayoutManager(context)
            layoutManager.reverseLayout = true
            recycler.layoutManager = layoutManager
            val adapter = ChallengeAdapter(list, playViewModel)
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }
}