package org.bedu.bonappetit.BonAppetitOrdersDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.bedu.bonappetit.Models.MyOrder

@Database(entities = arrayOf(MyOrder::class), version = 1)
abstract class OrdersDB:RoomDatabase() {

    companion object{
        private var dbInstance: OrdersDB? = null

        const val DB_NAME = "Orders_DB"

        fun getInstance(context: Context): OrdersDB?{
            if(dbInstance == null){
                synchronized(OrdersDB::class){
                    dbInstance = Room.databaseBuilder(
                        context.applicationContext,
                        OrdersDB::class.java,
                        DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return dbInstance
        }
    }

    abstract fun OrdersDao(): OrdersDao
}