package com.example.pokemonapp.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.rememberAsyncImagePainter
import com.example.pokemonapp.models.PokemonViewModel
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonScreen(pokemonViewModel: PokemonViewModel = viewModel()) {
    val pokemon by pokemonViewModel.pokemon.collectAsState()
    Column {
//        Banner()
        InputField(pokemonViewModel)
        Box(
            contentAlignment = Alignment.Center
        ) {
            Column(

            ) {
                pokemon?.let {
                    PokemonSprite(it.sprite.imageURL)
                    Text(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                        text = it.name,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp
                    )
                    Log.d("types", it.types.toString())
                    it.types?.let { typelist ->
                        Text(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .fillMaxWidth(),
                            text = "Types: " + typelist.joinToString("-") { typeModel -> typeModel.type.name },
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp
                        )
                    }
                    Text(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                        text = "Height: " + it.height.toString() + "ft",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                        text = "Weight: " + it.weight.toString() + "lbs",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp
                    )
                }
            }
        }
    }
}

@Composable
fun InputField(pokemonViewModel: PokemonViewModel) {

    val inputText = remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier.padding(4.dp)
    ) {
        OutlinedTextField(
            value = inputText.value,
            onValueChange = { inputText.value = it },
            label = {
                Text("Search")
            }
        )
        Button(onClick = {
            if (inputText.value != "") pokemonViewModel.fetchPokemon(
                inputText.value.lowercase(
                    Locale.getDefault()
                )
            )
        }) { Text("GO") } //.lowercase so I dont have to do only lower case characters to search
    }
}
//@Composable
//fun Banner() {
////    Image(
//////        painter = painterResource(id = R.drawable.banner), // Use the image file name without the extension
////        contentDescription = "Description of the image",
////        modifier = Modifier.size(100.dp) // Adjust size as needed
////    )
//}
@Composable
private fun PokemonSprite(url: String) {
    val painter = rememberAsyncImagePainter(url)
    Image(
        modifier = Modifier.size(200.dp),
        painter = painter,
        contentDescription = "",
    )
}
//TODO
//Pokemon banner