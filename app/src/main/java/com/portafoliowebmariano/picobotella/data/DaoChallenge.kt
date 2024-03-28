package com.portafoliowebmariano.picobotella.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.portafoliowebmariano.picobotella.model.Challenge

@Dao
interface DaoChallenge {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addChallenge(challenge: Challenge)

    @Query("SELECT * FROM Challenge")
    suspend fun getChallenge(): MutableList<Challenge>

    @Update
    suspend fun updateChallenge(challenge: Challenge)

    @Delete
    suspend fun deleteChallenge(challenge: Challenge)
}