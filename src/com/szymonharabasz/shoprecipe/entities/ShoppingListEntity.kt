package com.szymonharabasz.shoprecipe.entities

import java.io.Serializable

class ShoppingListEntity(
        val id: String,
        val name: String,
        val items: List<ListItemEntity>,
        val source: ShoppingListSource
) : Serializable {
}