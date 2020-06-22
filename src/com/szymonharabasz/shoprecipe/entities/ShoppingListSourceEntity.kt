package com.szymonharabasz.shoprecipe.entities

import java.io.Serializable

sealed class ShoppingListSourceEntity : Serializable {
}

class BookSourceEntity(val author: String, val title: String, val page: Int) : ShoppingListSourceEntity() {
    override fun toString(): String = "book \"$title\" by $author, page $page"
}

class WebpageSourceEntity(val name: String, val url: String) : ShoppingListSourceEntity() {
    override fun toString(): String = "webpage \"$name\", $url"
}

object NoSourceEntity : ShoppingListSourceEntity() {
    override fun toString(): String = ""
}