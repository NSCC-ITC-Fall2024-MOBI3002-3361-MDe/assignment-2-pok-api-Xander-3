package com.example.pokemonapp.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.retrofit.Api
import com.example.pokemonapp.retrofit.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val repository: Api = PokemonRepository()

) : ViewModel() {
    private val _pokemon = MutableStateFlow<PokemonModel?>(null)
    val pokemon: StateFlow<PokemonModel?> get() = _pokemon.asStateFlow()
    init{
        fetchPokemon("bulbasaur") //Why not initialize to pokemon 1?
    }
    fun fetchPokemon(name: String) {
        viewModelScope.launch {
            try {
                val result = repository.getPokemon(name)
                val error = result.errorBody()
                val data = result.body()
                if (error != null || !result.isSuccessful) {
                    Log.d("Got an error", "Got an error")
                    return@launch
                }
                if (data != null) {
                    Log.d("Result", result.toString())
                    _pokemon.value = data
                } else {
                    Log.d("Got nothing", "Got nothing")
                }
            } catch (e: Exception) {
                Log.e("Fetch Pokemon Error", "Error fetching Pokemon", e)
            }
        }
    }
}