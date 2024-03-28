package com.portafoliowebmariano.picobotella.view.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import com.portafoliowebmariano.picobotella.databinding.DialogAddChallengeBinding
import com.portafoliowebmariano.picobotella.model.Challenge
import com.portafoliowebmariano.picobotella.viewmodel.PlayViewModel

object DialogAddChallenge {
    fun showDialogAddChallenge(
        context: Context,
        playViewModel: PlayViewModel,
        updateList: () -> Unit,
    ) {
        val inflater = LayoutInflater.from(context)
        val binding = DialogAddChallengeBinding.inflate(inflater)
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setCancelable(false)
        alertDialog.setView(binding.root)

        binding.etContent.addTextChangedListener {
            binding.btnGuardar.isEnabled = binding.etContent.text.toString().isNotEmpty()
        }

        binding.btnCancelar.setOnClickListener {
            alertDialog.dismiss()
        }

        binding.btnGuardar.setOnClickListener {
            val descriptionChallenge = binding.etContent.text.toString().trim()
            val challenge = Challenge(DescriptionChallenge = descriptionChallenge)

            playViewModel.addChallenge(challenge)
            alertDialog.dismiss()
            updateList.invoke()
        }

        alertDialog.show()
    }
}