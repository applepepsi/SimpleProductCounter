package com.example.simpleproductcounter.repository

import com.example.simpleproductcounter.dataBase.DBDistributor
import com.example.simpleproductcounter.dataBase.DBItemData
import com.example.simpleproductcounter.dataBase.ItemAndDistributors
import com.example.simpleproductcounter.dataBase.ItemDataDao
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(
    private val itemDataDao: ItemDataDao
) :ProductRepository {

    override suspend fun insertBrand(dbItemData: DBItemData){

        itemDataDao.insertBrand(dbItemData=dbItemData)
    }

    override suspend fun deleteBrand(dbItemData: DBItemData){

    }

    override suspend fun insertDistributor(dbDistributor: DBDistributor){
        itemDataDao.insertDistributor(dbDistributor = dbDistributor)
    }

    override suspend fun deleteDistributor(dbDistributor: DBDistributor){

    }

    override fun getAllProductData(): Flow<List<ItemAndDistributors>> {
        return itemDataDao.getAllProductData()
    }

}