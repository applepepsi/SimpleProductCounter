package com.example.oneplusone.mudule

import android.content.Context
import androidx.room.Room


import com.example.simpleproductcounter.dataBase.Database
import com.example.simpleproductcounter.dataBase.ItemDataDao
import com.example.simpleproductcounter.repository.ProductRepository
import com.example.simpleproductcounter.repository.ProductRepositoryImpl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =

        Room.databaseBuilder(
            context,
            Database::class.java,
            "product_database"
        )   .addMigrations()
            .fallbackToDestructiveMigration()
            .build()



    @Provides
    fun provideItemData(itemDataBase: Database) = itemDataBase.itemDataDao()

    @Provides
    fun provideProductRepository(itemDataDao: ItemDataDao)
            : ProductRepository {
        return ProductRepositoryImpl(itemDataDao)
    }
}