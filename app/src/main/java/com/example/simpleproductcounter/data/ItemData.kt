package com.example.simpleproductcounter.data

data class ItemData(
    val brand:String="Brand",
    val distributor: List<Distributor> = emptyList()
)

data class Distributor(
    val distributor:String="유통 업체",
    val count:Int=0
)
