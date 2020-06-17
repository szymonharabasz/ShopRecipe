package com.szymonharabasz.shoprecipe.dao

import com.szymonharabasz.shoprecipe.entities.BookSource
import com.szymonharabasz.shoprecipe.entities.ListItemEntity
import com.szymonharabasz.shoprecipe.entities.ShoppingListEntity
import com.szymonharabasz.shoprecipe.viewmodel.ListItem
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.inject.Named

@Named
@ApplicationScoped
open class ShoppingListFakeDao : ShoppingListDao {
    private var shoppingLists: List<ShoppingListEntity> = listOf(
            ShoppingListEntity(
                    "1",
                    "Pesto-Hänchen in Blätterteig",
                    listOf(
                            ListItemEntity(0, "Fertig augerollter Blättrteig", 320.0, "g"),
                            ListItemEntity(1, "Hänchenbrustfillets", 4.0, "Stück"),
                            ListItemEntity(2, "Pesto", 4.0, "TL gehäuftet"),
                            ListItemEntity(3, "Kleine reife Rispentomaten", 400.0, "g"),
                            ListItemEntity(4, "Grüne Bohnen", 400.0, "g")
                    ),
                    BookSource("Jamie Oliver", "Jamies 5 Zutaten Küche", 114)
            ),
            ShoppingListEntity(
                    "2",
                    "Stir-fired diced chicken",
                    listOf(
                            ListItemEntity(5, "Dried chillis, soaked in water for 20 minutes", 3.0, ""),
                            ListItemEntity(6, "Vegetsble oil", 30.0, "ml"),
                            ListItemEntity(7, "Garlic cloves, chopped", 3.0, ""),
                            ListItemEntity(8, "Chicken breast fillet", 450.0, "g"),
                            ListItemEntity(9, "Hoisin sauce", 15.0, "ml"),
                            ListItemEntity(10, "Rice wine", 15.0, "ml"),
                            ListItemEntity(11, "Yellow bean sauce", 15.0, "ml"),
                            ListItemEntity(12, "Sugar", 2.5, "ml"),
                            ListItemEntity(13, "Cornflour (cornstarch)", 5.0, "ml"),
                            ListItemEntity(14, "Spring onion (scallion), chopped", 1.0, "")
                    ),
                    BookSource("Terry Tan", "The food and cooking of Shanghai and East China", 77)
            )
    )
    private var usedIds: SortedSet<Long> = shoppingLists.flatMap { shopingListEntity ->
        shopingListEntity.items.map { listItemEntity -> listItemEntity.id }
    }.toSortedSet()

    override fun getNextId(): Long =
        when (val list = usedIds.zipWithNext().filter { it.second != it.first + 1 }) {
            listOf<Pair<Long, Long>>() -> usedIds.last() + 1
            else -> list.first().first + 1
        }

    override fun addItem(listId: String, item: ListItemEntity): Unit {
        shoppingLists = shoppingLists.map { shoppingList ->
            if (shoppingList.id == listId)
                ShoppingListEntity(listId, shoppingList.name, shoppingList.items + item, shoppingList.source)
            else
                shoppingList
        }
        usedIds.add(item.id)
    }

    override fun getShoppingLists(): List<ShoppingListEntity> = shoppingLists
    override fun removeItem(listId: String, itemId: Long): Unit {
        shoppingLists = shoppingLists.map { shoppingList ->
            if (shoppingList.id == listId)
                ShoppingListEntity(
                        listId,
                        shoppingList.name,
                        shoppingList.items.filter { it.id != itemId },
                        shoppingList.source
                )
            else
                shoppingList
        }
    }

    override fun updateItem(listId: String, item: ListItem) {
        shoppingLists = shoppingLists.map { shoppingList ->
            if (shoppingList.id == listId)
                ShoppingListEntity(
                        listId,
                        shoppingList.name,
                        shoppingList.items.map {listItemEntity ->
                            if (listItemEntity.id == item.id)
                                ListItemEntity(item.id, item.name, item.quantity, item.unit)
                            else
                                listItemEntity
                        },
                        shoppingList.source
                )
            else
                shoppingList
        }
    }
}
