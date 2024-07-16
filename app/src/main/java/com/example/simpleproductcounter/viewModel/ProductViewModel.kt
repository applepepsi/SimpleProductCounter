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

    private var _setShowBrandModifyDialog= mutableStateOf(false)

    val setShowBrandModifyDialog: State<Boolean> = _setShowBrandModifyDialog

    private var _selectBrand= mutableStateOf(ItemData())
    val selectBrand: State<ItemData> = _selectBrand


    private var _allProductCount= mutableStateOf(0)
    val allProductCount: State<Int> = _allProductCount

    private var _setShowDeleteIcon= mutableStateOf(true)

    val setShowDeleteIcon: State<Boolean> = _setShowDeleteIcon

    init{
        getAllProductData()
        getAllProductCount()
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

    fun toggleShowBrandModifyDialog(){
        _setShowBrandModifyDialog.value=!_setShowBrandModifyDialog.value
        Log.d("_setShowItemModifyDialog.value", _setShowBrandModifyDialog.value.toString())
        if(!_setShowBrandModifyDialog.value){
            resetBrandData()
        }
    }

    fun toggleSetShowDeleteIcon(){
        _setShowDeleteIcon.value=!_setShowDeleteIcon.value

    }

    private fun resetSelectData() {
        _selectDistributorData.value = Distributor()
    }

    private fun resetBrandData() {
        _selectBrand.value=ItemData()
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
        _selectBrand.value = _selectBrand.value.copy(brand = value)
    }



    fun postBrandName(){
        viewModelScope.launch {

            productRepository.insertBrand(DBItemData(brand = selectBrand.value.brand))
        }
    }

    fun postModifyBrandName(){
        viewModelScope.launch {

            Log.d("DBItemData(brand = selectBrand.value.brand)",
                DBItemData(brand = selectBrand.value.brand).toString()
            )
            productRepository.modifyBrand(DBItemData(selectBrand.value.brandId,selectBrand.value.brand))
        }
    }

    fun postAddDistributor(){

        val addDistributorData=DBDistributor(
            distributor = _selectDistributorData.value.distributor,
            count = _selectDistributorData.value.count,
            itemId = _selectBrand.value.brandId
        )


        viewModelScope.launch {

            productRepository.insertDistributor(addDistributorData)
        }
    }

    fun postModifyDistributor(){
        val addDistributorData=DBDistributor(
            id=_selectDistributorData.value.distributorId,
            distributor = _selectDistributorData.value.distributor,
            count = _selectDistributorData.value.count,
            itemId = _selectBrand.value.brandId
        )
        viewModelScope.launch {
            Log.d("addDistributorData",
                addDistributorData.toString()
            )
            productRepository.modifyDistributor(addDistributorData)
        }
    }

    fun deleteBrandName(){
        viewModelScope.launch {

            productRepository.deleteBrand(_selectBrand.value.brandId)
        }
    }

    fun deleteDistributorData(){
        viewModelScope.launch {
            Log.d("_selectDistributorData.value.distributorId",
                _selectDistributorData.value.distributorId.toString()
            )
            productRepository.deleteDistributor(_selectDistributorData.value.distributorId)
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

    fun setSelectBrand(brandId: ItemData) {
        Log.d("selectBrand", brandId.toString())
        _selectBrand.value=brandId
    }

    fun getAllProductCount() {
        viewModelScope.launch {
            productRepository.getAllProductCount()
                .collect { allProductCount->
                    Log.d("allProductCount", allProductCount.toString())
                    _allProductCount.value = allProductCount
                }
        }
    }

}
