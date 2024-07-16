package com.example.simpleproductcounter.dataBase
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface ItemDataDao {

    @Insert
    suspend fun insertBrand(dbItemData: DBItemData): Long

    @Query("DELETE FROM DBItemData WHERE id = :brandIdNum")
    suspend fun deleteBrand(brandIdNum: Int)

    @Insert
    suspend fun insertDistributor(dbDistributor: DBDistributor)

    @Query("DELETE FROM DBDistributor WHERE id = :dbDistributorIdNum")
    suspend fun deleteDistributor(dbDistributorIdNum: Int)



    //변경된 데이터를 받아오고 싶다면 Flow를 사용해라 그리고 Flow는 자체적으로 비동기를 지원한다
    //Flow는 데이터가 변경될때마다 새로운 데이터를 방출한다 뷰모델에서는 이 데이터를 구독한다
    @Transaction
    @Query("SELECT * FROM DBItemData")
    fun getAllProductData(): Flow<List<ItemAndDistributors>>

    @Transaction
    @Query("SELECT IFNULL(SUM(count), 0) FROM DBDistributor")
    fun getAllProductCounter(): Flow<Int>

    @Query("UPDATE DBDistributor SET distributor=:newDistributor, count=:count ,itemId = :dbBrandIdNum WHERE id = :id")
    suspend fun modifyDistributor(dbBrandIdNum: Int,newDistributor:String,count:Int,id:Int)

    @Query("UPDATE DBItemData SET brand=:newBrandName WHERE id = :brandIdNum")
    suspend fun modifyBrand(brandIdNum: Int,newBrandName:String)
}