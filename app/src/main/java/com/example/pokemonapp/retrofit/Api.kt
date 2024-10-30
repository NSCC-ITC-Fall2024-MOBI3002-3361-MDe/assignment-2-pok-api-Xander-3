package com.example.pokemonapp.retrofit

import com.example.pokemonapp.models.PokemonModel
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("api/v2/pokemon/{name}")
    suspend fun getPokemon(
        @Path("name")name:String
    ): Response<PokemonModel>
}
interface PokemonRepositoryInterface {
    suspend fun getPokemon(name: String): Response<PokemonModel>
}
fun getRetrofitClient(): Api{
    val client = Retrofit.Builder()
        .baseUrl("https://pokeapi.co")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .build()
    return client.create(Api::class.java)
}
class PokemonRepository(
    private val api: Api = getRetrofitClient()
): Api {
    override suspend fun getPokemon(name: String): Response<PokemonModel> {
        return api.getPokemon(name)
    }
}
//object RetrofitInstance{
//    val api: Api by lazy {
//        Retrofit.Builder()
//            .baseUrl("https://pokeapi.co/api/v2/pokemon")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(Api::class.java)
//    }
//}

//TODO
//I need to figure out how to call and search as well.