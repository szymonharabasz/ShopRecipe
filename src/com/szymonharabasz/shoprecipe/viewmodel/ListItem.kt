package com.szymonharabasz.shoprecipe.viewmodel

import java.io.Serializable

data class ListItem(var id: Long = 0, var editable: Boolean = false, var name: String, var quantity: Double, var unit: String) : Serializable {
    override fun toString(): String {
        return "id: $id, editable: ${editable}, name: $name, qty: $quantity $unit"
    }
    companion object {
        fun empty(): ListItem = ListItem(-1,true, "", 0.0, "")
    }
}