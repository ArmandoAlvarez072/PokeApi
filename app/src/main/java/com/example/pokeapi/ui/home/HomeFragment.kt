package com.example.pokeapi.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokeapi.databinding.FragmentPokemonBinding
import com.example.pokeapi.entities.Pokemon
import java.util.ArrayList

class HomeFragment : Fragment() {

    private lateinit var pokemonViewModel: PokemonViewModel
    private lateinit var adapter: PokemonAdapter
    private var _binding: FragmentPokemonBinding? = null
    private var pokemonList : List<Pokemon> = ArrayList()


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         pokemonViewModel =
            ViewModelProvider(this).get(PokemonViewModel::class.java)

        _binding = FragmentPokemonBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRecyclerView()
        configButton()
    }

    private fun configButton() {
        _binding?.let{
            it.btnSearch.setOnClickListener {
                pokemonViewModel.getPokemonList().observe(viewLifecycleOwner, Observer {
                    pokemonList = it
                    adapter.setData(pokemonList)
                })
            }
        }
    }

    private fun configRecyclerView() {
        _binding?.let{
            adapter = PokemonAdapter(arrayListOf())
            it.recyclerView.apply {
                layoutManager = GridLayoutManager(context, 1,
                    GridLayoutManager.VERTICAL,
                    false)
                adapter = this@HomeFragment.adapter
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}