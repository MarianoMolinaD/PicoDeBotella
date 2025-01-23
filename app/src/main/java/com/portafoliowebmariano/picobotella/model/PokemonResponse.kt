package com.portafoliowebmariano.picobotella.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("pokemon")
    var pokemon : MutableList<Pokemon> = mutableListOf()
)
