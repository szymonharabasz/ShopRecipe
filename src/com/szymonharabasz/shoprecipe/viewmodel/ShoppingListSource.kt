package com.szymonharabasz.shoprecipe.viewmodel

class ShoppingListSource (
        val type: ShoppingListSourceType = ShoppingListSourceType.NO_SOURCE,
        val bookTitle: String = "",
        val bookAuthor: String = "",
        val bookPage: Int = 0,
        val webpageName: String = "",
        val webpageUrl: String = ""
) {
}