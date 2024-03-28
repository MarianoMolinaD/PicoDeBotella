package com.portafoliowebmariano.picobotella.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Challenge(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var DescriptionChallenge: String,
)
