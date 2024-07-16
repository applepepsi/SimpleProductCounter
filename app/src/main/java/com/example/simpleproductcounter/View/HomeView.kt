package com.example.simpleproductcounter.View

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simpleproductcounter.R
import com.example.simpleproductcounter.component.AppBarTextComponent
import com.example.simpleproductcounter.component.ItemComponent
import com.example.simpleproductcounter.component.ModifyOrAddBrandDialog
import com.example.simpleproductcounter.component.ModifyOrAddProductDataDialog
import com.example.simpleproductcounter.data.Distributor
import com.example.simpleproductcounter.data.ItemData
import com.example.simpleproductcounter.repository.TestRepository
import com.example.simpleproductcounter.viewModel.ProductViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainView(productViewModel: ProductViewModel) {


    if(productViewModel.setShowItemModifyDialog.value){
        ModifyOrAddProductDataDialog(
            productViewModel,
            value = "수정 하기",
            toggleDialogState = { productViewModel.toggleShowItemModifyDialog() },
            kindOfPost = {productViewModel.postModifyDistributor()}
        )
    }
    if(productViewModel.setShowItemAddDialog.value){
        ModifyOrAddProductDataDialog(
            productViewModel,
            value = "추가 하기",
            toggleDialogState = { productViewModel.toggleShowItemAddDialog() },
            kindOfPost = {productViewModel.postAddDistributor()}
        )
    }
    if(productViewModel.setShowBrandAddDialog.value){
        ModifyOrAddBrandDialog(
            productViewModel,
            value = "브랜드 추가",
            toggleDialogState = { productViewModel.toggleShowBrandAddDialog() },
            kindOfPost = {productViewModel.postBrandName()}
        )
    }
    if(productViewModel.setShowBrandModifyDialog.value){
        ModifyOrAddBrandDialog(
            productViewModel,
            value = "브랜드 수정",
            toggleDialogState = { productViewModel.toggleShowBrandModifyDialog() },
            kindOfPost = {productViewModel.postModifyBrandName()}
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
    ) {

        AppBarTextComponent(
            value = "상품 관리"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){

            TextButton(
                onClick = {
                    productViewModel.toggleSetShowDeleteIcon()
                }
            ) {
                Text(
                    text ="삭제 아이콘 표시",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                    ),
                    color= Color.Black
                )
            }

            IconButton(
                onClick = {
                    productViewModel.toggleShowBrandAddDialog()
                },
                modifier = Modifier

                    .padding(end = 10.dp)
                    .size(25.dp)
            ){
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.plus_icon),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp),
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .wrapContentHeight()

        ) {

            item{
                for(singleBrand in productViewModel.brandDataList.value){
                    ItemComponent(
                        singleBrand=singleBrand,
                        productViewModel
                    )
                }
            }

            item{
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)

                        .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp)),
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text="총 상품 수:${productViewModel.allProductCount.value}",
                        modifier = Modifier
                            .padding(5.dp)
                    )
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    val testRepository= TestRepository()
    val productViewModel=ProductViewModel(testRepository)


    MaterialTheme {
        MainView(productViewModel)
    }
}