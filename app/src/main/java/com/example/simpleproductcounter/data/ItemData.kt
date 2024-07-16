package com.example.simpleproductcounter.data

data class ItemData(
    val brandId:Int=0,
    val brand:String="",
    val distributor: List<Distributor> = emptyList()
)

data class Distributor(
    val distributorId:Int=0,
    val distributor:String="",
    val count:Int=1,
    val brandId:Int=0
)

