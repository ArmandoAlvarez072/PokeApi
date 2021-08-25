package com.example.pokeapi.ui.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pokeapi.R
import com.example.pokeapi.databinding.FragmentMapsBinding
import com.example.pokeapi.entities.Location
import com.example.pokeapi.entities.Pokemon
import com.example.pokeapi.ui.home.PokemonViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() , OnMapReadyCallback{
    private lateinit var pokemonViewModel: PokemonViewModel
    private var mMap: GoogleMap? = null
    private var pokemonList : MutableList<Pokemon>? = null

    private var _binding: FragmentMapsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pokemonViewModel =
            ViewModelProvider(this).get(PokemonViewModel::class.java)

        _binding = FragmentMapsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onMapReady(map: GoogleMap?) {
        mMap = map
        pokemonViewModel.getPokemonList().observe(viewLifecycleOwner, Observer {
            it.forEach {
                val lattitude = (-100..100).random().toDouble()
                val longitude = (-100..100).random().toDouble()
                val location = Location(lattitude, longitude)
                it.location = location
                pokemonList?.add(it)
                mMap?.addMarker(MarkerOptions()
                    .position(LatLng(lattitude, longitude))
                    .title(it.name)
                    .snippet(it.url))
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.onResume()

        mapView.getMapAsync(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}