package com.example.simpleproductcounter.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.simpleproductcounter.data.Distributor
import com.example.simpleproductcounter.data.ItemData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProductViewModel  @Inject constructor(

): ViewModel() {


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
}
