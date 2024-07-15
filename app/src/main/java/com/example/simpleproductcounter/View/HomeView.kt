package com.example.simpleproductcounter.View

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simpleproductcounter.R
import com.example.simpleproductcounter.component.AddBrandDialog
import com.example.simpleproductcounter.component.AppBarTextComponent
import com.example.simpleproductcounter.component.ItemComponent
import com.example.simpleproductcounter.component.ModifyOrAddProductDataDialog
import com.example.simpleproductcounter.data.Distributor
import com.example.simpleproductcounter.data.ItemData
import com.example.simpleproductcounter.viewModel.ProductViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainView(){

    val productViewModel:ProductViewModel= hiltViewModel()

    if(productViewModel.setShowItemModifyDialog.value){
        ModifyOrAddProductDataDialog(productViewModel, value = "수정 하기", toggleDialogState = { productViewModel.toggleShowItemModifyDialog() })
    }
    if(productViewModel.setShowItemAddDialog.value){
        ModifyOrAddProductDataDialog(productViewModel, value = "추가 하기", toggleDialogState = { productViewModel.toggleShowItemAddDialog() })
    }
    if(productViewModel.setShowBrandAddDialog.value){
        AddBrandDialog(productViewModel, value = "브랜드 추가", toggleDialogState = { productViewModel.toggleShowBrandAddDialog() })
    }


    Column(
        modifier = Modifier.fillMaxSize().padding(top=10.dp)
    ) {

        AppBarTextComponent(
            value = "상품 관리"
        )
        IconButton(
            onClick = {
                productViewModel.toggleShowBrandAddDialog()
            },
            modifier = Modifier
                .align(Alignment.End)
                .size(25.dp)
        ){
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.plus_icon),
                contentDescription = null,
                modifier = Modifier.size(25.dp),
            )
        }
        LazyColumn(
            modifier = Modifier
                .wrapContentHeight()

        ) {

            item{
                for(singleBrand in testData){
                    ItemComponent(
                        singleBrand=singleBrand,
                        productViewModel
                    )
                }
            }
        }
    }
}


val testData= listOf(
    ItemData(
        brand = "나이키",
        distributor =
        listOf(
            Distributor(distributor = "천안 백화점", count = 3),
            Distributor(distributor = "공주 백화점", count = 3),
        ),

    ),
    ItemData(
        brand = "아이다스",
        distributor =
        listOf(
            Distributor(distributor = "고려 백화점", count = 3),
            Distributor(distributor = "신라 백화점", count = 3),
        ),

    ),
    ItemData(
        brand = "나이키",
        distributor =
        listOf(
            Distributor(distributor = "고구려 백화점", count = 3),
            Distributor(distributor = "백제 백화점", count = 3),
        ),
    ),
    ItemData(
        brand = "아이다스",
        distributor =
        listOf(
            Distributor(distributor = "설악산 백화점", count = 3),
            Distributor(distributor = "백악관 백화점", count = 3),
        ),
    ),
    ItemData(
        brand = "아이다스",
        distributor =
        listOf(
            Distributor(distributor = "설악산 백화점", count = 3),
            Distributor(distributor = "백악관 백화점", count = 3),
        ),
    ),
    ItemData(
        brand = "아이다스",
        distributor =
        listOf(
            Distributor(distributor = "설악산 백화점", count = 3),
            Distributor(distributor = "백악관 백화점", count = 3),
        ),
    ),
)


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {

    val productViewModel=ProductViewModel()

    MaterialTheme {
        MainView()
    }
}