package org.bedu.bonappetit.BonAppetitOrdersDB

import androidx.room.*
import org.bedu.bonappetit.Models.MyOrder

@Dao
interface OrdersDao {

    @Insert
    fun insertOrder(myOrder: MyOrder)

    @Update
    fun updateOrder(myOrder: MyOrder)

    @Delete
    fun removeOrder(myOrder: MyOrder)

    //@Query("SELECT * FROM MY_ORDERS")
    //fun getOrder(myOrder: MyOrder): List<MyOrder>

    @Query("SELECT id, itemSelected, cost  FROM MY_ORDERS")
    fun getMyOrders(): List<MyOrder>

    @Query("DELETE FROM MY_ORDERS WHERE id=:id")
    fun removeOrderById(id: Int)

    @Query("DELETE FROM MY_ORDERS")
    fun cleanMyOrder()
}