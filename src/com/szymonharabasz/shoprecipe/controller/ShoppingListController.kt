package com.szymonharabasz.shoprecipe.controller

import com.szymonharabasz.shoprecipe.dao.ShoppingListDao
import com.szymonharabasz.shoprecipe.entities.ListItemEntity
import com.szymonharabasz.shoprecipe.dao.ShoppingListFakeDao
import com.szymonharabasz.shoprecipe.viewmodel.ListItem
import com.szymonharabasz.shoprecipe.viewmodel.ShoppingList
import java.io.Serializable
import javax.annotation.PostConstruct
import javax.faces.view.ViewScoped
import javax.inject.Inject
import javax.inject.Named

@Named
@ViewScoped
class ShoppingListController(var name: String = "Szymon") : Serializable {
    @Inject
    lateinit private var shoppingListDao: ShoppingListDao

    lateinit private var shoppingLists: MutableList<ShoppingList>

    @PostConstruct
    fun fetchShoppingLists(): Unit {
        System.out.println("FETCHING SHOPPING LIST FROM DAO")
        shoppingLists = shoppingListDao.getShoppingLists().map {listEntity ->
            ShoppingList(
                    listEntity.id,
                    listEntity.name,
                    listEntity.items.map { listItemEntity ->
                        ListItem(listItemEntity.id,
                                false,
                                listItemEntity.name,
                                listItemEntity.quantity,
                                listItemEntity.unit)
                    } + ListItem.empty(),
                    listEntity.source)
        }.toMutableList()
    }
    fun addItem(list: ShoppingList, item: ListItem): String {
        println("Adding $item to id = ${list.id}")
        shoppingListDao.addItem(list.id, ListItemEntity(shoppingListDao.getNextId(), item.name, item.quantity, item.unit))
        return "index"
    }
    fun updateItem(list: ShoppingList, item: ListItem): String {
        shoppingListDao.updateItem(list.id, item)
        return "index"
    }
    fun removeItem(list: ShoppingList, item: ListItem): String {
        shoppingListDao.removeItem(list.id, item.id)
        return "index"
    }
    fun makeEditable(item: ListItem): Unit { item.editable = true }
    fun getShoppingLists(): MutableList<ShoppingList> {
        return shoppingLists
    }
    fun setShoppingLists(lists: MutableList<ShoppingList>): Unit {
        shoppingLists = lists
    }
}