package com.example.simpleproductcounter.repository

import com.example.simpleproductcounter.dataBase.DBDistributor
import com.example.simpleproductcounter.dataBase.DBItemData
import com.example.simpleproductcounter.dataBase.ItemAndDistributors
import com.example.simpleproductcounter.dataBase.ItemDataDao
import com.example.simpleproductcounter.dataBase.TestItemDataDao
import kotlinx.coroutines.flow.Flow

class TestRepository(

) :ProductRepository {

    private val itemDataDao: ItemDataDao= TestItemDataDao()

    override suspend fun insertBrand(dbItemData: DBItemData){

    }

    override suspend fun deleteBrand(brandIdNum: Int){
        itemDataDao.deleteBrand(brandIdNum)
    }

    override suspend fun modifyBrand(dbItemData: DBItemData) {
        TODO("Not yet implemented")
    }

    override suspend fun insertDistributor(dbDistributor: DBDistributor){

    }

    override suspend fun deleteDistributor(dbDistributorIdNum: Int){

    }

    override suspend fun modifyDistributor(dbDistributor: DBDistributor) {
        TODO("Not yet implemented")
    }

    override fun getAllProductData(): Flow<List<ItemAndDistributors>> {
        return itemDataDao.getAllProductData()
    }

    override fun getAllProductCount(): Flow<Int> {
        TODO("Not yet implemented")
    }

}