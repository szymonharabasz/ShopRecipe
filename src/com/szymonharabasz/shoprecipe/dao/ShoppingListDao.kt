package com.szymonharabasz.shoprecipe.dao

import com.szymonharabasz.shoprecipe.entities.ListItemEntity
import com.szymonharabasz.shoprecipe.entities.ShoppingListEntity
import com.szymonharabasz.shoprecipe.viewmodel.ListItem

interface ShoppingListDao {
    fun getNextId(): Long
    fun addItem(listId: String, item: ListItemEntity)
    fun getShoppingLists(): List<ShoppingListEntity>
    fun removeItem(listId: String, itemId: Long): Unit
    fun updateItem(listId: String, item: ListItem)
}