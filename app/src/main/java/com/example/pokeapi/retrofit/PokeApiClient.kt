package com.example.pokeapi.retrofit

import com.example.pokeapi.common.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeApiClient {
    private val pokeApiService : PokeApiService
    private val retrofit : Retrofit

    companion object{
        var instance: PokeApiClient? = null
            get() {
                if (field == null){
                    instance = PokeApiClient()
                }
                return field
            }
    }

    init {
        val okHttpClient = OkHttpClient.Builder().build()

        retrofit = Retrofit.Builder()
            .baseUrl(Constants.POKEAPI_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        pokeApiService = retrofit.create(PokeApiService::class.java)
    }

    fun getPokeApiService() = pokeApiService
}