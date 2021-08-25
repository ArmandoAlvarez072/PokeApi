package com.example.pokeapi.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.pokeapi.common.MyApp
import com.example.pokeapi.entities.Pokemon
import com.example.pokeapi.entities.PokemonResponse
import com.example.pokeapi.retrofit.PokeApiClient
import com.example.pokeapi.retrofit.PokeApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokeApiRepository {
    private var pokeApiService: PokeApiService? = null
    private var pokeApiClient : PokeApiClient? = null
    private var pokemonList : MutableLiveData<List<Pokemon>>? = null

    init {
        pokeApiClient = PokeApiClient.instance
        pokeApiService = pokeApiClient?.getPokeApiService()
        pokemonList = pokemonList()
    }

    fun pokemonList() : MutableLiveData<List<Pokemon>> {
        if (pokemonList == null){
            pokemonList = MutableLiveData<List<Pokemon>>()
        }

        val call : Call<PokemonResponse>? = pokeApiService?.getPokemon()
        call?.enqueue(object : Callback<PokemonResponse>{
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                if (response.isSuccessful){
                    pokemonList?.value= response.body()?.results
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error en la llamada", Toast.LENGTH_LONG).show()
            }
        })



        return pokemonList!!
    }
}