package com.example.simpleproductcounter.repository

import android.util.Log
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

    override suspend fun deleteBrand(brandIdNum: Int){
        itemDataDao.deleteBrand(brandIdNum)
    }

    override suspend fun modifyBrand(dbItemData: DBItemData) {
        itemDataDao.modifyBrand(brandIdNum = dbItemData.id, newBrandName = dbItemData.brand)
    }

    override suspend fun insertDistributor(dbDistributor: DBDistributor){
        itemDataDao.insertDistributor(dbDistributor = dbDistributor)
    }

    override suspend fun deleteDistributor(dbDistributorIdNum: Int){
        itemDataDao.deleteDistributor(dbDistributorIdNum = dbDistributorIdNum)
    }

    override suspend fun modifyDistributor(dbDistributor: DBDistributor) {
        Log.d("dbDistributor", dbDistributor.toString())
        itemDataDao.modifyDistributor(dbBrandIdNum = dbDistributor.itemId, newDistributor = dbDistributor.distributor, count = dbDistributor.count,id=dbDistributor.id)
    }

    override fun getAllProductData(): Flow<List<ItemAndDistributors>> {
        return itemDataDao.getAllProductData()
    }

    override fun getAllProductCount(): Flow<Int> {
        return itemDataDao.getAllProductCounter()
    }


}