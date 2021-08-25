package com.example.pokeapi.entities

data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Pokemon>
)