package com.szymonharabasz.shoprecipe.entities

import java.io.Serializable

open class ShoppingListSource : Serializable {
}

class BookSource(val author: String, val title: String, val page: Int) : ShoppingListSource() {
    override fun toString(): String = "book \"$title\" by $author, page $page"
}

class WebpageSource(val name: String, val url: String) : ShoppingListSource() {
    override fun toString(): String = "webpage \"$name\", $url"
}