package com.example.simpleproductcounter.viewModel

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



    fun setSelectItemData(itemData:ItemData){
        _brandData.value=itemData
    }

    fun toggleShowItemModifyDialog(){
        _setShowItemModifyDialog.value=!_setShowItemModifyDialog.value
        if(!_setShowItemModifyDialog.value){
            resetSelectData()
        }
    }

    private fun resetSelectData() {
        _selectDistributorData.value = Distributor()
    }


    fun deleteItem(){

    }

    fun updateModifyDistributorText(value:String){

        _selectDistributorData.value = _selectDistributorData.value.copy(distributor = value)
    }

    fun updateModifyCount(value:Int){
        _selectDistributorData.value = _selectDistributorData.value.copy(count = value)
    }
}
