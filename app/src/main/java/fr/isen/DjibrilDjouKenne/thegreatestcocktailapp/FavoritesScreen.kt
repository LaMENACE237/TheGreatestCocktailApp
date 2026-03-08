package fr.isen.DjibrilDjouKenne.thegreatestcocktailapp

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import fr.isen.DjibrilDjouKenne.thegreatestcocktailapp.ui.FavoritesViewModel

@Composable
fun FavoritesScreen(modifier: Modifier = Modifier) {
    val vm: FavoritesViewModel = viewModel()
    val state = vm.state.collectAsState().value
    val context = LocalContext.current

    if (state.isLoading) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    if (state.favorites.isEmpty()) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Aucun favori pour l’instant 🥲")
        }
        return
    }
    if (state.error != null) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Erreur: ${state.error}")
        }
        return
    }

    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(state.favorites) { fav ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable {
                        val intent = Intent(context, CocktailDetailActivity::class.java)
                        intent.putExtra("drink_id", fav.idDrink)
                        context.startActivity(intent)
                    }
            ) {
                Row(modifier = Modifier.padding(12.dp)) {
                    AsyncImage(
                        model = fav.thumb,
                        contentDescription = fav.name,
                        modifier = Modifier.size(64.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(fav.name, style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}