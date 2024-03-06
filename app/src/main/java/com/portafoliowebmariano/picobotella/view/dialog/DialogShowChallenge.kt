package com.portafoliowebmariano.picobotella.view.dialog

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.portafoliowebmariano.picobotella.databinding.DialogShowChallengeBinding

object DialogShowChallenge {

    fun showDialogChallenge(context : Context, audioBackground : MediaPlayer,isMute:Boolean,messageChallenge: String){

        val inflater = LayoutInflater.from(context)
        val binding = DialogShowChallengeBinding.inflate(inflater)

        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.setCancelable(false)
        alertDialog.setView(binding.root)

        binding.tvChagenlle.text = messageChallenge


        binding.btnClose.setOnClickListener {
            if (!isMute){
                audioBackground.start()
            }
            alertDialog.dismiss()
        }

        alertDialog.show()
    }
}