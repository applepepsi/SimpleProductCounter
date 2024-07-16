package com.example.simpleproductcounter.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DBItemData::class, DBDistributor::class], version = 1)
abstract class Database:RoomDatabase() {

    abstract fun itemDataDao(): ItemDataDao
}