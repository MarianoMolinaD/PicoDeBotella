package com.portafoliowebmariano.picobotella.service

import com.portafoliowebmariano.picobotella.model.PokemonResponse
import com.portafoliowebmariano.picobotella.utils.constants.END_POINT_POKEMON
import retrofit2.http.GET

interface ApiService {
    @GET(END_POINT_POKEMON)
    suspend fun getPokemon() : PokemonResponse
}