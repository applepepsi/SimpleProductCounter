package com.example.simpleproductcounter.dataBase

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Relation

@Entity
data class DBItemData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val brand: String
)


@Entity(
    foreignKeys = [ForeignKey(
        entity = DBItemData::class,
        parentColumns = ["id"],
        childColumns = ["itemId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class DBDistributor(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val distributor: String,
    val count: Int,
    val itemId: Int
)


data class ItemAndDistributors(

    @Embedded
    val item: DBItemData,

    @Relation(
        parentColumn = "id",
        entityColumn = "itemId"
    )
    val distributors: List<DBDistributor>
)