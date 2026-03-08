package fr.isen.DjibrilDjouKenne.thegreatestcocktailapp

import kotlin.jvm.java

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import fr.isen.DjibrilDjouKenne.thegreatestcocktailapp.ui.CategoriesViewModel


@Composable
fun CategoriesScreen(modifier: Modifier = Modifier) {
    val vm: CategoriesViewModel = viewModel()
    val state = vm.state.collectAsState().value
    val context = LocalContext.current

    when {
        state.isLoading -> {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.error != null -> {
            Column(modifier.padding(16.dp)) {
                Text("Erreur: ${state.error}")
                Spacer(Modifier.height(8.dp))
                Button(onClick = { vm.loadCategories() }) { Text("Réessayer") }
            }
        }

        else -> {
            LazyColumn(modifier = modifier.padding(16.dp)) {
                items(state.categories) { catName ->
                    Surface(
                        tonalElevation = 2.dp,
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .clickable {
                                val intent = Intent(context, DrinksListActivity::class.java)
                                intent.putExtra("category_name", catName)
                                context.startActivity(intent)
                            }
                    ) {
                        Text(catName, modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}