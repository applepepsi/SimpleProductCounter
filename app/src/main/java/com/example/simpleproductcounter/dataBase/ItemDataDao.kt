package com.example.simpleproductcounter.dataBase
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface ItemDataDao {

    @Insert
    suspend fun insertBrand(dbItemData: DBItemData): Long

    @Delete
    suspend fun deleteBrand(dbItemData: DBItemData)

    @Insert
    suspend fun insertDistributor(dbDistributor: DBDistributor)

    @Delete
    suspend fun deleteDistributor(dbDistributor: DBDistributor)



    //변경된 데이터를 받아오고 싶다면 Flow를 사용해라 그리고 Flow는 자체적으로 비동기를 지원한다
    //Flow는 데이터가 변경될때마다 새로운 데이터를 방출한다 뷰모델에서는 이 데이터를 구독한다
    @Transaction
    @Query("SELECT * FROM DBItemData")
    fun getAllProductData(): Flow<List<ItemAndDistributors>>
}