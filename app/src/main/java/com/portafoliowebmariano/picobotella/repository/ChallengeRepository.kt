package com.portafoliowebmariano.picobotella.repository

import android.content.Context
import com.portafoliowebmariano.picobotella.data.DBChallenge
import com.portafoliowebmariano.picobotella.data.DaoChallenge
import com.portafoliowebmariano.picobotella.model.Challenge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChallengeRepository(val context: Context) {
    var daoChallenge: DaoChallenge = DBChallenge.getDataBase(context).daoChallenge()

    suspend fun addChallenge(challenge : Challenge){
        withContext(Dispatchers.IO){
            daoChallenge.addChallenge(challenge)
        }
    }

    suspend fun getListChallenge(): MutableList<Challenge>{
        return withContext(Dispatchers.IO){
            daoChallenge.getChallenge()
        }
    }
    suspend fun deleteChallenge(challenge: Challenge){
        withContext(Dispatchers.IO){
            daoChallenge.deleteChallenge(challenge)
        }
    }


}