package com.example.pokemonapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonModel(
    @SerializedName("name") val name: String,
    @SerializedName("weight") val weight: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("types") val types: List<PokemonTypeModel>,
    @SerializedName("sprites") val sprite: PokemonSpritesModel
): Parcelable

@Parcelize
data class TypeModel(
    @SerializedName("name") val name: String
): Parcelable

@Parcelize
data class PokemonTypeModel(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: TypeModel
): Parcelable

@Parcelize
data class PokemonSpritesModel(
    @SerializedName("front_default") val imageURL: String
): Parcelable

//@Parcelize
//data class Pokemon(
//    val name: String,
//    val weight: Int,
//    val height: Int,
//    val types: List<Type>,
//    val sprite: Sprite
//): Parcelable
//
//@Parcelize
//data class Type(
//    val name: String
//):Parcelable
//
//@Parcelize
//data class Sprite(
//    val imageURL: String
//): Parcelable

//TODO
//I hate nested data