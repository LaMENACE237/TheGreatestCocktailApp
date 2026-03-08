package fr.isen.DjibrilDjouKenne.thegreatestcocktailapp.data.model

data class CategoriesResponse(
    val drinks: List<CategoryItem>?
)

data class CategoryItem(
    val strCategory: String?
)