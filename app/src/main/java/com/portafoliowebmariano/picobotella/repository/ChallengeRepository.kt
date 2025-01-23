package com.portafoliowebmariano.picobotella.repository

import android.content.Context
import com.portafoliowebmariano.picobotella.data.DBChallenge
import com.portafoliowebmariano.picobotella.data.DaoChallenge
import com.portafoliowebmariano.picobotella.model.Challenge
import com.portafoliowebmariano.picobotella.model.Pokemon
import com.portafoliowebmariano.picobotella.service.ApiService
import com.portafoliowebmariano.picobotella.service.ApiUtlis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChallengeRepository(val context: Context) {
    private var daoChallenge: DaoChallenge = DBChallenge.getDataBase(context).daoChallenge()
    private var apiService : ApiService = ApiUtlis.getApiServices()

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

    suspend fun updateChallenge(challenge: Challenge){
        withContext(Dispatchers.IO){
            daoChallenge.updateChallenge(challenge)
        }
    }
    suspend fun getListPokemon(): MutableList<Pokemon>{
        return withContext(Dispatchers.IO){
            val listPokemon = apiService.getPokemon()
            listPokemon.pokemon
        }
    }
}