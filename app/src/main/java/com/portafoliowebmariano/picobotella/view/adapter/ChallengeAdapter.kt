package com.portafoliowebmariano.picobotella.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.portafoliowebmariano.picobotella.databinding.ItemChallengeBinding
import com.portafoliowebmariano.picobotella.model.Challenge
import com.portafoliowebmariano.picobotella.view.viewholder.ChallengeViewHolder
import com.portafoliowebmariano.picobotella.viewmodel.PlayViewModel

class ChallengeAdapter(
    private val listaChallenge: MutableList<Challenge>,
    private val playViewModel: PlayViewModel,
) :
    RecyclerView.Adapter<ChallengeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        val binding = ItemChallengeBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ChallengeViewHolder(binding, playViewModel)
    }

    override fun getItemCount(): Int = listaChallenge.size

    override fun onBindViewHolder(challengeViewHolder: ChallengeViewHolder, position: Int) {
        val challenge = listaChallenge[position]
        challengeViewHolder.setItemChallenge(challenge)
    }
}