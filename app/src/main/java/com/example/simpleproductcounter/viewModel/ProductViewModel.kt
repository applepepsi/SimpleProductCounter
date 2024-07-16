package com.example.simpleproductcounter.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleproductcounter.data.Distributor
import com.example.simpleproductcounter.data.ItemData
import com.example.simpleproductcounter.dataBase.DBDistributor
import com.example.simpleproductcounter.dataBase.DBItemData
import com.example.simpleproductcounter.dataBase.ItemAndDistributors
import com.example.simpleproductcounter.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel  @Inject constructor(
    private val productRepository: ProductRepository
): ViewModel() {

    private var _brandDataList = mutableStateOf<List<ItemData>>(emptyList())
    val brandDataList: State<List<ItemData>> = _brandDataList


    private var _brandData= mutableStateOf(ItemData())

    val brandData: State<ItemData> = _brandData

    private var _selectDistributorData= mutableStateOf(Distributor())

    val selectDistributorData: State<Distributor> = _selectDistributorData

    private var _setShowItemModifyDialog= mutableStateOf(false)

    val setShowItemModifyDialog: State<Boolean> = _setShowItemModifyDialog

    private var _setShowItemAddDialog= mutableStateOf(false)

    val setShowItemAddDialog: State<Boolean> = _setShowItemAddDialog

    private var _setShowBrandAddDialog= mutableStateOf(false)

    val setShowBrandAddDialog: State<Boolean> = _setShowBrandAddDialog

    private var _singleBrandText= mutableStateOf("")

    val singleBrandText: State<String> = _singleBrandText

    private var _selectBrand= mutableStateOf(0)
    val selectBrand: State<Int> = _selectBrand
    init{
        getAllProductData()
    }


    fun setSelectItemData(itemData:Distributor){
        _selectDistributorData.value=itemData
    }

    fun toggleShowItemModifyDialog(){
        _setShowItemModifyDialog.value=!_setShowItemModifyDialog.value
        Log.d("_setShowItemModifyDialog.value", _setShowItemModifyDialog.value.toString())
        if(!_setShowItemModifyDialog.value){
            resetSelectData()
        }
    }

    fun toggleShowItemAddDialog(){
        _setShowItemAddDialog.value=!_setShowItemAddDialog.value
        Log.d("_setShowItemModifyDialog.value", _setShowItemAddDialog.value.toString())
        if(!_setShowItemAddDialog.value){
            resetSelectData()
        }
    }

    fun toggleShowBrandAddDialog(){
        _setShowBrandAddDialog.value=!_setShowBrandAddDialog.value
        Log.d("_setShowItemModifyDialog.value", _setShowBrandAddDialog.value.toString())
        if(!_setShowBrandAddDialog.value){
            resetBrandData()
        }
    }

    private fun resetSelectData() {
        _selectDistributorData.value = Distributor()
    }

    private fun resetBrandData() {
        _singleBrandText.value=""
    }


    fun deleteItem(){

    }

    fun updateModifyDistributorText(value:String){

        _selectDistributorData.value = _selectDistributorData.value.copy(distributor = value)
    }

    fun updateModifyCount(value:Int){
        _selectDistributorData.value = _selectDistributorData.value.copy(count = value)
    }

    fun updateBrandText(value:String){
        _singleBrandText.value = value
    }



    fun postBrandName(){
        viewModelScope.launch {

            productRepository.insertBrand(DBItemData(brand = singleBrandText.value))
        }
    }



    fun postAddDistributor(){

        val addDistributorData=DBDistributor(
            distributor = _selectDistributorData.value.distributor,
            count = _selectDistributorData.value.count,
            itemId = _selectBrand.value
        )

        viewModelScope.launch {

            productRepository.insertDistributor(addDistributorData)
        }
    }

    fun getAllProductData() {
        viewModelScope.launch {
            productRepository.getAllProductData()
                .collect { allProductDataList ->
                    val itemDataList = allProductDataList.toItemDataList()
                    _brandDataList.value = itemDataList
                }
        }
    }

    private fun ItemAndDistributors.toItemData(): ItemData {
        return ItemData(
            brandId = this.item.id,
            brand = this.item.brand,
            distributor = this.distributors.map { distributor ->
                Distributor(
                    distributorId = distributor.id,
                    distributor = distributor.distributor,
                    count = distributor.count,
                    brandId = distributor.itemId
                )
            }
        )
    }

    private fun List<ItemAndDistributors>.toItemDataList(): List<ItemData> {
        return this.map { it.toItemData() }
    }

    fun setSelectBrand(brandId: Int) {
        _selectBrand.value=brandId
    }

}
