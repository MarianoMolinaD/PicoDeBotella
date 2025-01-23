package com.portafoliowebmariano.picobotella.view.dialog

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.Glide
import com.portafoliowebmariano.picobotella.R
import com.portafoliowebmariano.picobotella.databinding.DialogShowChallengeBinding
import com.portafoliowebmariano.picobotella.model.Pokemon
import com.portafoliowebmariano.picobotella.viewmodel.PlayViewModel

object DialogShowChallenge {

    @RequiresApi(Build.VERSION_CODES.M)
    fun showDialogChallenge(context : Context, audioBackground : MediaPlayer, isMute:Boolean, messageChallenge: String, playViewModel: PlayViewModel){

        val inflater = LayoutInflater.from(context)
        val binding = DialogShowChallengeBinding.inflate(inflater)
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.setCancelable(false)
        alertDialog.setView(binding.root)

//        if(playViewModel.isNetworkAvailable(context)){
//            Glide.with(context).load(pokemon?.img).into(binding.ivChallenge)
//        }else{
//            Toast.makeText(context, "No tienes conexion a internet", Toast.LENGTH_SHORT).show()
//            binding.ivChallenge.setImageResource(R.drawable.logoutiltek)
//        }


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