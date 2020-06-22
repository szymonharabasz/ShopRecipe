package com.szymonharabasz.shoprecipe.dao

import com.szymonharabasz.shoprecipe.entities.ListItemEntity
import com.szymonharabasz.shoprecipe.entities.ShoppingListEntity
import com.szymonharabasz.shoprecipe.viewmodel.ListItem
import javax.enterprise.context.ApplicationScoped
import javax.inject.Named

@Named
@ApplicationScoped
@RemoteRepository
class ShoppingListRemoteDao : ShoppingListDao {
    override fun getNextId(): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addItem(listId: String, item: ListItemEntity) {

    }

    override fun getShoppingLists(): List<ShoppingListEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeItem(listId: String, itemId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateItem(listId: String, item: ListItemEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateList(list: ShoppingListEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}