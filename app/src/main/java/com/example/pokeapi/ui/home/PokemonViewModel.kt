package com.example.pokeapi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokeapi.entities.Pokemon
import com.example.pokeapi.repository.PokeApiRepository

class PokemonViewModel : ViewModel() {
    private var pokeApiRepository : PokeApiRepository
    private var pokemonList : LiveData<List<Pokemon>>

    init {
        pokeApiRepository = PokeApiRepository()
        pokemonList = pokeApiRepository.pokemonList()
    }

    fun getPokemonList() : LiveData<List<Pokemon>> {
        return pokemonList
    }

}