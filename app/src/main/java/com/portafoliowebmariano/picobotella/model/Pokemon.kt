package com.portafoliowebmariano.picobotella.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id")
    var id: Long,
    @SerializedName("img")
    var img: String,
)
