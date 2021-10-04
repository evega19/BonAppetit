package org.bedu.bonappetit.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="my_orders")
data class MyOrder(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo val itemSelected: String?,
    @ColumnInfo val cost: Double?,
)