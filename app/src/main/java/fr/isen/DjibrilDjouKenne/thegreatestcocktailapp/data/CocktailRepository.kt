package fr.isen.DjibrilDjouKenne.thegreatestcocktailapp.data

import fr.isen.DjibrilDjouKenne.thegreatestcocktailapp.data.api.RetrofitClient
import fr.isen.DjibrilDjouKenne.thegreatestcocktailapp.data.model.Drink
import fr.isen.DjibrilDjouKenne.thegreatestcocktailapp.data.model.DrinkItem
import fr.isen.DjibrilDjouKenne.thegreatestcocktailapp.data.model.CategoryItem
import fr.isen.DjibrilDjouKenne.thegreatestcocktailapp.data.model.CategoriesResponse
class CocktailRepository {
    private val api = RetrofitClient.api

    suspend fun random(): Drink? = api.getRandomCocktail().drinks?.firstOrNull()
    suspend fun byId(id: String): Drink? = api.getCocktailById(id).drinks?.firstOrNull()

    suspend fun categories(): List<String> {
        val items = api.getCategories().drinks.orEmpty()
        return items.mapNotNull { it.strCategory?.trim() }
    }

    suspend fun drinksByCategory(category: String): List<DrinkItem> {
        return api.getDrinksByCategory(category).drinks.orEmpty()
    }
}
