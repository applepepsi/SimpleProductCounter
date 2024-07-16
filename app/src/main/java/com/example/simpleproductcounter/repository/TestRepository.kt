package com.example.simpleproductcounter.repository

import com.example.simpleproductcounter.dataBase.DBDistributor
import com.example.simpleproductcounter.dataBase.DBItemData
import com.example.simpleproductcounter.dataBase.ItemAndDistributors
import com.example.simpleproductcounter.dataBase.ItemDataDao
import kotlinx.coroutines.flow.Flow

class TestRepository(

) :ProductRepository {

    private val itemDataDao: ItemDataDao = TODO()

    override suspend fun insertBrand(dbItemData: DBItemData){

    }

    override suspend fun deleteBrand(dbItemData: DBItemData){

    }

    override suspend fun insertDistributor(dbDistributor: DBDistributor){

    }

    override suspend fun deleteDistributor(dbDistributor: DBDistributor){

    }

    override fun getAllProductData(): Flow<List<ItemAndDistributors>> {
        return itemDataDao.getAllProductData()
    }

}