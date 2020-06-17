package com.szymonharabasz.shoprecipe.entities

import java.io.Serializable

class ListItemEntity(var id: Long, var name: String, var quantity: Double, var unit: String) : Serializable {
    override fun toString(): String {
        return "id: $id, name: $name, qty: $quantity $unit"
    }
}