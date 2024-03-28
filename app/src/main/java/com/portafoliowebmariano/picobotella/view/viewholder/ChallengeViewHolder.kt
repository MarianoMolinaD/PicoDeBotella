package com.portafoliowebmariano.picobotella.view.viewholder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.portafoliowebmariano.picobotella.databinding.DialogUpdateChallengeBinding
import com.portafoliowebmariano.picobotella.databinding.ItemChallengeBinding
import com.portafoliowebmariano.picobotella.model.Challenge
import com.portafoliowebmariano.picobotella.view.dialog.DiallogUpdateChallenge.showDialogUpdateChallenge
import com.portafoliowebmariano.picobotella.view.dialog.DialogDeleteChallenge
import com.portafoliowebmariano.picobotella.viewmodel.PlayViewModel

class ChallengeViewHolder(binding : ItemChallengeBinding,playViewModel: PlayViewModel) : ViewHolder(binding.root) {
    private val viewModel = playViewModel
    private var binding : ItemChallengeBinding
    init {
        this.binding = binding
    }
    fun setItemChallenge(challenge: Challenge) {
        binding.tvDescription.text = challenge.DescriptionChallenge

        binding.ivDelete.setOnClickListener {
            val dialog = DialogDeleteChallenge(binding.root.context, viewModel, challenge)
            dialog.show()
        }
        binding.ivEdit.setOnClickListener {
            showDialogUpdateChallenge(binding.root.context, viewModel, challenge){
                viewModel.getListChallenge()
            }
        }
    }
}