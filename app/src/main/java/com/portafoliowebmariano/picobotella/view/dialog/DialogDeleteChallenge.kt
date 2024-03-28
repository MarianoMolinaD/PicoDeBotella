package com.portafoliowebmariano.picobotella.view.dialog

import android.app.AlertDialog
import android.content.Context
import com.portafoliowebmariano.picobotella.R
import com.portafoliowebmariano.picobotella.model.Challenge
import com.portafoliowebmariano.picobotella.viewmodel.PlayViewModel

fun DialogDeleteChallenge(context: Context, playViewModel: PlayViewModel, challenge: Challenge):AlertDialog{

    val builder = AlertDialog.Builder(context)
    builder.setCancelable(false)
    builder.setTitle(R.string.title_dialog_eliminar)
        .setMessage("\n${challenge.DescriptionChallenge}")
        .setPositiveButton("SI"){dialog, with ->
            playViewModel.deleteChallenge(challenge)
            dialog.dismiss()
            playViewModel.getListChallenge()
        }
        .setNegativeButton("NO"){dialog, with ->
            dialog.dismiss()
        }

    return builder.create()
}