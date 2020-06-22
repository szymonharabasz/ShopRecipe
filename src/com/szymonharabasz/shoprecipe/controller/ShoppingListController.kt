package com.szymonharabasz.shoprecipe.controller

import com.szymonharabasz.shoprecipe.dao.InMemoryRepository
import com.szymonharabasz.shoprecipe.dao.ShoppingListDao
import com.szymonharabasz.shoprecipe.entities.*
import com.szymonharabasz.shoprecipe.viewmodel.ListItem
import com.szymonharabasz.shoprecipe.viewmodel.ShoppingList
import com.szymonharabasz.shoprecipe.viewmodel.ShoppingListSource
import com.szymonharabasz.shoprecipe.viewmodel.ShoppingListSourceType
import java.io.Serializable
import javax.annotation.PostConstruct
import javax.faces.view.ViewScoped
import javax.inject.Inject
import javax.inject.Named

@Named
@ViewScoped
class ShoppingListController(var name: String = "Szymon") : Serializable {
    @InMemoryRepository
    @Inject
    lateinit private var shoppingListDao: ShoppingListDao

    lateinit private var shoppingLists: MutableList<ShoppingList>

    @PostConstruct
    fun fetchShoppingLists(): Unit {
        System.out.println("FETCHING SHOPPING LIST FROM DAO")
        shoppingLists = (shoppingListDao.getShoppingLists()
                .map { shoppingListFromEntity(it) } + ShoppingList.empty())
                .toMutableList()
    }
    fun addItem(list: ShoppingList, item: ListItem): String {
        println("Adding $item to id = ${list.id}")
        shoppingListDao.addItem(list.id, ListItemEntity(shoppingListDao.getNextId(), item.name, item.quantity, item.unit))
        return "index"
    }
    fun updateItem(list: ShoppingList, item: ListItem): String {
        shoppingListDao.updateItem(list.id, listItemToEntity(item))
        return "index"
    }
    fun updateHeader(list: ShoppingList): String {

        return "index"
    }
    fun removeItem(list: ShoppingList, item: ListItem): String {
        shoppingListDao.removeItem(list.id, item.id)
        return "index"
    }
    fun makeEditable(item: ListItem): Unit {
        println("SETTING ITEM EDITABLE")
        item.editable = true }
    fun makeHeaderEditable(shoppingList: ShoppingList): Unit {
        println("SETTING HEADER EDITABLE")
        shoppingList.headerEditable = true
    }
    fun getShoppingLists(): MutableList<ShoppingList> {
        return shoppingLists
    }
    fun setShoppingLists(lists: MutableList<ShoppingList>): Unit {
        shoppingLists = lists
    }
    private fun listItemToEntity(listItem: ListItem): ListItemEntity =
            ListItemEntity(listItem.id, listItem.name, listItem.quantity, listItem.unit)
    private fun listItemFromEntity(listItemEntity: ListItemEntity): ListItem =
            ListItem(listItemEntity.id, false, listItemEntity.name, listItemEntity.quantity,
                    listItemEntity.unit)
    private fun shopptingListToEntity(shoppingList: ShoppingList): ShoppingListEntity =
            ShoppingListEntity(shoppingList.id, shoppingList.name,
                    shoppingList.items.filter { it != ListItem.empty() }.map { listItemToEntity(it) },
                    sourceToEntity(shoppingList.source))
    private fun shoppingListFromEntity(shoppingListEntity: ShoppingListEntity): ShoppingList =
            ShoppingList(false, shoppingListEntity.id, shoppingListEntity.name,
                    shoppingListEntity.items.map { listItemFromEntity(it) } + ListItem.empty(),
                    sourceFromEntity(shoppingListEntity.sourceEntity))
    private fun sourceToEntity(source: ShoppingListSource): ShoppingListSourceEntity = when (source.type) {
        ShoppingListSourceType.BOOK_SOURCE ->
            BookSourceEntity(source.bookAuthor, source.bookTitle, source.bookPage)
        ShoppingListSourceType.WEBPAGE_SOURCE ->
            WebpageSourceEntity(source.webpageName, source.webpageUrl)
        ShoppingListSourceType.NO_SOURCE -> NoSourceEntity
    }
    private fun sourceFromEntity(sourceEntity: ShoppingListSourceEntity): ShoppingListSource = when (sourceEntity) {
        is BookSourceEntity ->
            ShoppingListSource(
                    ShoppingListSourceType.BOOK_SOURCE,
                    sourceEntity.title,
                    sourceEntity.author,
                    sourceEntity.page, "", "")
        is WebpageSourceEntity ->
            ShoppingListSource(
                    ShoppingListSourceType.WEBPAGE_SOURCE, "", "", 0,
                    sourceEntity.name,
                    sourceEntity.url)
        is NoSourceEntity -> ShoppingListSource()
    }
}