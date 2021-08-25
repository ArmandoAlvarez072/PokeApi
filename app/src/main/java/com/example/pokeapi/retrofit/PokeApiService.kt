package com.example.pokeapi.retrofit

import com.example.pokeapi.entities.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET

interface PokeApiService {

    @GET("pokemon")
    fun getPokemon() : Call<PokemonResponse>
}