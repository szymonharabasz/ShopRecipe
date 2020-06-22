package com.szymonharabasz.shoprecipe.viewmodel

import com.szymonharabasz.shoprecipe.entities.*

data class ShoppingList (
        var headerEditable: Boolean,
        val id: String,
        var name: String,
        var items: List<ListItem>,
        val source: ShoppingListSource) {

    val BOOK_SOURCE: String = "book_source"
    val WEBPAGE_SOURCE: String = "webpage_source"

    override fun toString(): String {
        return "id: $id, name: $name, source: $source\n" + items.map { "- $it\n" }.reduce { a, b -> "$a$b" } + "\n"
    }
    companion object {
        fun empty(): ShoppingList =
                ShoppingList(true, "", "", listOf(ListItem.empty()),
                        ShoppingListSource())
    }
}