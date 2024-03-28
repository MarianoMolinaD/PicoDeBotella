package com.portafoliowebmariano.picobotella.view.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import androidx.core.widget.addTextChangedListener
import com.portafoliowebmariano.picobotella.databinding.DialogUpdateChallengeBinding
import com.portafoliowebmariano.picobotella.model.Challenge
import com.portafoliowebmariano.picobotella.viewmodel.PlayViewModel

object DiallogUpdateChallenge {

    fun showDialogUpdateChallenge(
        context: Context,
        playViewModel: PlayViewModel,
        challenge: Challenge,
        updateList: () -> Unit,
    ) {
        val inflater = LayoutInflater.from(context)
        val binding = DialogUpdateChallengeBinding.inflate(inflater)
        var alerDialog = AlertDialog.Builder(context).create()

        alerDialog.setCancelable(false)
        alerDialog.setView(binding.root)

        binding.etContent.addTextChangedListener {
            binding.btnGuardar.isEnabled = binding.etContent.text.toString().isNotEmpty()
        }
        binding.etContent.setText(challenge.DescriptionChallenge)

        binding.btnCancelar.setOnClickListener {
            alerDialog.dismiss()
        }
        binding.btnGuardar.setOnClickListener {
            val description = binding.etContent.text.toString().trim()
            val challenge = Challenge(challenge.id,  description)

            playViewModel.updateChallenge(challenge)
            alerDialog.dismiss()
            updateList.invoke()
        }
        alerDialog.show()
    }
}