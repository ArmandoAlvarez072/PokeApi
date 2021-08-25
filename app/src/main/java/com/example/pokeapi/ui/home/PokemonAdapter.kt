package com.example.pokeapi.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapi.R
import com.example.pokeapi.databinding.ItemPokemonBinding
import com.example.pokeapi.entities.Location
import com.example.pokeapi.entities.Pokemon
import kotlin.random.Random

class PokemonAdapter(private var pokemonList : MutableList<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemPokemonBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.binding.tvName.text = context.getString(R.string.name_pokemon, pokemon.name)
        holder.binding.tvUrl.text = context.getString(R.string.url_pokemon, pokemon.url)

        holder.binding.tvLattitude.text = context.getString(R.string.tv_latitud, pokemon.location.latitude)
        holder.binding.tvLongitude.text = context.getString(R.string.tv_longitud, pokemon.location.longitude)

    }

    fun setData(pokemons: List<Pokemon>){
        pokemons.forEach {
            val lattitude = (-100..100).random().toDouble()
            val longitude = (-100..100).random().toDouble()
            val location = Location(lattitude, longitude)
            it.location = location
            pokemonList.add(it)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = pokemonList.size

}