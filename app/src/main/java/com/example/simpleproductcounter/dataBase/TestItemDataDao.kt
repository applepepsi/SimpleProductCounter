package com.example.simpleproductcounter.dataBase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TestItemDataDao : ItemDataDao {

    override fun getAllProductData(): Flow<List<ItemAndDistributors>> {

        val testData = listOf(
            ItemAndDistributors(
                item = DBItemData(id = 1, brand = "Test Item"),
                distributors = listOf(DBDistributor(id = 1, distributor = "Test Distributor", count = 1, itemId = 1))
            )
        )
        return flowOf(testData)
    }

    override fun getAllProductCounter(): Flow<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun modifyDistributor(
        dbBrandIdNum: Int,
        newDistributor: String,
        count: Int,
        id: Int
    ) {
        TODO("Not yet implemented")
    }


    override suspend fun modifyBrand(brandIdNum: Int, newBrandName: String) {
        TODO("Not yet implemented")
    }


    override suspend fun insertBrand(dbItemData: DBItemData): Long {
        return 1
    }

    override suspend fun deleteBrand(brandIdNum: Int) {

    }

    override suspend fun insertDistributor(dbDistributor: DBDistributor) {

    }

    override suspend fun deleteDistributor(dbDistributorIdNum: Int) {

    }

}