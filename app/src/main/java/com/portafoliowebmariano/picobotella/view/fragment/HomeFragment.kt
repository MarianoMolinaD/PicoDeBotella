package com.portafoliowebmariano.picobotella.view.fragment

import android.media.MediaParser
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.portafoliowebmariano.picobotella.R
import com.portafoliowebmariano.picobotella.databinding.FragmentHomeBinding
import com.portafoliowebmariano.picobotella.viewmodel.PlayViewModel
import kotlinx.coroutines.runBlocking

class HomeFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var audioBackground: MediaPlayer
    private lateinit var audioSpinBottle: MediaPlayer
    private lateinit var audioShowChallenge: MediaPlayer
    private lateinit var audioButton: MediaPlayer
    private lateinit var audioSuspense: MediaPlayer

    private var isMute: Boolean = true

    private val playViewModel: PlayViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controladores(view)
        observerViewModel()
        mediaController()
    }

    private fun mediaController() {
        audioBackground = MediaPlayer.create(context, R.raw.music_background)
        audioSpinBottle = MediaPlayer.create(context, R.raw.audio_bottle)
        audioShowChallenge = MediaPlayer.create(context, R.raw.audio_challenge)
        audioButton = MediaPlayer.create(context, R.raw.audio_button)
        audioSuspense = MediaPlayer.create(context, R.raw.audio_suspense)
        audioBackground.start()
    }

    private fun controladores(view: View) {
        navController = Navigation.findNavController(view)

        binding.icContainerMenu.icRules.setOnClickListener {
            audioBackground.pause()
            findNavController().navigate(R.id.action_homeFragment_to_rulesPlayFragment)
            playViewModel.statusShowDialog(false)
        }

        binding.btnSpin.setOnClickListener {
            playViewModel.spinBottle()
        }
        binding.icContainerMenu.icMuteOff.setOnClickListener {
            isMute = true
            binding.icContainerMenu.icMuteOn.isVisible = isMute
            binding.icContainerMenu.icMuteOff.isVisible = !isMute
            audioBackground.pause()

        }
        binding.icContainerMenu.icMuteOn.setOnClickListener {
            isMute = false
            binding.icContainerMenu.icMuteOff.isVisible = !isMute
            binding.icContainerMenu.icMuteOn.isVisible = isMute
            audioBackground.start()
        }
        binding.icContainerMenu.icAddChallenge.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addChallengeFragment)
        }
    }

    private fun observerViewModel() {
        observerRotationBottle()
        observerEnableButton()
        observerEnableStreamers()
        observerDialogChallenge()
    }

    private fun observerDialogChallenge() {
        playViewModel.statusShowDialog.observe(viewLifecycleOwner) {
            if (it) {
                runBlocking {
                    audioSuspense.start()
                    playViewModel.wait(3)
                }
                audioSuspense.pause()
                val messageChallenge = "Debes tomar un trago"
                playViewModel.dialogShowChallenge(
                    requireContext(),
                    audioBackground, isMute,
                    messageChallenge
                )
                audioShowChallenge.start()
                audioSpinBottle.pause()
                audioButton.pause()
            }
        }
    }

    private fun observerEnableStreamers() {
        playViewModel.enableStreamers.observe(viewLifecycleOwner) { enableStreamer ->
            binding.lottieCerpentina.isVisible = enableStreamer
            binding.lottieCerpentina.playAnimation()
        }
    }

    private fun observerEnableButton() {
        playViewModel.enableButton.observe(viewLifecycleOwner) { enableButton ->
            binding.btnSpin.isVisible = enableButton
        }
    }

    private fun observerRotationBottle() {
        playViewModel.statusRotationBottle.observe(viewLifecycleOwner) { statusRotation ->
            if (statusRotation) {
                audioButton.start()
                audioBackground.pause()
                audioSpinBottle.start()
                playViewModel.rotationBottle.observe(viewLifecycleOwner) { rotation ->
                    binding.ivBottle.startAnimation(rotation)
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        audioBackground.stop()
    }

    override fun onPause() {
        super.onPause()
        audioBackground.pause()
    }

    override fun onResume() {
        super.onResume()
        if (!isMute) {
            audioBackground.start()
        }
    }
}