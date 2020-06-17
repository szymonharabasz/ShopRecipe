package com.szymonharabasz.shoprecipe.viewmodel

import com.szymonharabasz.shoprecipe.entities.ListItemEntity
import com.szymonharabasz.shoprecipe.entities.ShoppingListEntity
import com.szymonharabasz.shoprecipe.entities.ShoppingListSource
import java.io.Serializable

class ShoppingList (
        val id: String,
        val name: String,
        var items: List<ListItem>,
        val source: ShoppingListSource) : Serializable {
    override fun toString(): String {
        return "id: $id, name: $name, source: $source\n" + items.map { "- $it\n" }.reduce { a, b -> "$a$b" } + "\n"
    }
}