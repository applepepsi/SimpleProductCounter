package com.example.simpleproductcounter.repository

import com.example.simpleproductcounter.dataBase.DBDistributor
import com.example.simpleproductcounter.dataBase.DBItemData
import com.example.simpleproductcounter.dataBase.ItemAndDistributors
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun insertBrand(dbItemData: DBItemData)

    suspend fun deleteBrand(brandIdNum: Int)

    suspend fun modifyBrand(dbItemData: DBItemData)

    suspend fun insertDistributor(dbDistributor: DBDistributor)

    suspend fun deleteDistributor(dbDistributorIdNum: Int)

    suspend fun modifyDistributor(dbDistributor: DBDistributor)



    fun getAllProductData(): Flow<List<ItemAndDistributors>>

    fun getAllProductCount(): Flow<Int>
}