package com.example.simpleproductcounter.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column



import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.simpleproductcounter.R
import com.example.simpleproductcounter.data.Distributor
import com.example.simpleproductcounter.data.ItemData
import com.example.simpleproductcounter.repository.TestRepository
import com.example.simpleproductcounter.viewModel.ProductViewModel

@Composable
fun ItemComponent(
    singleBrand: ItemData,
    productViewModel: ProductViewModel
) {



    Column(
        modifier = Modifier
            .fillMaxWidth()

            .padding(10.dp)
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth(),

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp, start = 10.dp),

                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically

            ) {
                TextButton(
                    onClick = {
                        productViewModel.setSelectBrand(singleBrand)
                        productViewModel.toggleShowBrandModifyDialog()
                    }
                )
                {
                    Text(
                        text = singleBrand.brand,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal,
                        ),
                        color = Color.Black,
                        modifier = Modifier.padding(end=10.dp)
                    )
                }
                if(productViewModel.setShowDeleteIcon.value){
                    IconButton(
                        onClick = {
                            productViewModel.setSelectBrand(singleBrand)
                            productViewModel.deleteBrandName()
                        },
                        modifier = Modifier
                            .size(20.dp)


                    ){
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.delete_button),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                }
            }

            DividerComponent()

            for(i in singleBrand.distributor) {
                DistributorComponent(
                    singleItem = i,
                    deleteItem = {
                        productViewModel.deleteDistributorData()
                    },
                    toggleDialogState = {
                        productViewModel.toggleShowItemModifyDialog()
                    },
                    setSelectDistributor={
                        productViewModel.setSelectItemData(i)
                    },
                    setSelectBrand={
                        productViewModel.setSelectBrand(singleBrand)
                    },
                    deleteButtonVisibility = productViewModel.setShowDeleteIcon.value
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){



            TextButton(
                onClick = {
                    productViewModel.setSelectBrand(singleBrand)
                    productViewModel.toggleShowItemAddDialog()
                },
                content = {
                    Text(
                        text = "추가하기",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal,
                        ),
                        color = Color.DarkGray
                    )
                },
//                modifier = Modifier.align(Alignment.End)
            )

            Text(
                text="합계: ${singleBrand.distributor.sumOf { it.count }}",
                modifier = Modifier.padding(end=10.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                ),
            )
        }
    }


}

@Composable
fun DistributorComponent(
    singleItem: Distributor,
    setSelectDistributor:()->Unit,
    deleteItem:()->Unit,
    toggleDialogState:()->Unit,
    setSelectBrand:()->Unit,
    deleteButtonVisibility:Boolean=false
) {



    Row(
        modifier = Modifier
            .fillMaxWidth()

            .clickable {
                setSelectBrand()
                setSelectDistributor()
                toggleDialogState()
            },
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Box(
            modifier = Modifier

                .weight(1f)
                .heightIn(min = 30.dp),

            contentAlignment = Alignment.Center,

        ) {
            Text(
                text = singleItem.distributor,
                style = TextStyle(
                    fontStyle = FontStyle.Normal,
                    fontSize = 12.sp
                ),
                textAlign = TextAlign.Center,

            )
        }

//        Spacer(modifier = Modifier.width(1.dp))

        Box(
            modifier = Modifier

                .weight(1f)
                .heightIn(min = 30.dp),

            contentAlignment = Alignment.Center
        ) {
            Text(
                text = singleItem.count.toString(),
                style = TextStyle(
                    fontStyle = FontStyle.Normal,
                    fontSize = 15.sp
                ),
                textAlign = TextAlign.Center
            )

            if(deleteButtonVisibility){
                IconButton(
                    onClick = {
                        setSelectDistributor()
                        deleteItem()
                    },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                ){
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.delete_button),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                    )
                }
            }
        }
    }
    Column {
        DividerComponent()
    }
}

@Composable
fun DividerComponent(

) {
    Divider(
        color = Color.DarkGray,
        modifier = Modifier
//            .padding(start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.White)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ModifyOrAddProductDataDialog(
    productViewModel: ProductViewModel,
    toggleDialogState: () -> Unit,
    kindOfPost:()->Unit,
    value:String="추가 하기"
    ){



    Dialog(
        onDismissRequest = { toggleDialogState() }
    ) {

        Column(
            modifier= Modifier
                .size(height = 170.dp, width = 300.dp)

                .background(Color.White),

            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text=value,
                modifier = Modifier
                    .padding(start=10.dp,bottom=5.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier
                    .padding(start=5.dp,end=5.dp)
                    ,
                horizontalArrangement = Arrangement.SpaceAround
            ){
                TextField(
                    shape = RoundedCornerShape(10.dp),
                    value = productViewModel.selectDistributorData.value.distributor,
                    onValueChange = {
                        productViewModel.updateModifyDistributorText(it)

                    },
                    maxLines = 1,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,

                    ),

                    modifier = Modifier
                        .weight(1f)
                        .border(width = 1.dp, color = Color.Gray)
                )

                Spacer(modifier = Modifier.width(5.dp))

                TextField(
                    shape = RoundedCornerShape(10.dp),
                    value = productViewModel.selectDistributorData.value.count.toString(),
                    onValueChange = {
                        if(it.isNotEmpty()){
                            productViewModel.updateModifyCount(it.toInt())

                        }
                    },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.NumberPassword
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .border(width = 1.dp, color = Color.Gray)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,

            ) {
                TextButton(
                    onClick = {
                        kindOfPost()
                        toggleDialogState()
                    },
                    modifier = Modifier
                ) {
                    Text(
                        text = "확인",
                        style = TextStyle(
                            fontStyle = FontStyle.Normal,
                            fontSize = 12.sp
                        ),
                        color= Color.Black
                    )
                }
                TextButton(
                    onClick = {
                        toggleDialogState()
                    },
                    modifier = Modifier
                ) {
                    Text(
                        text = "취소",
                        style = TextStyle(
                            fontStyle = FontStyle.Normal,
                            fontSize = 12.sp
                        ),
                        color= Color.Black
                    )
                }

            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ModifyOrAddBrandDialog(
    productViewModel: ProductViewModel,
    toggleDialogState: () -> Unit,
    kindOfPost:()->Unit,
    value:String="브랜드 추가"
){



    Dialog(
        onDismissRequest = { toggleDialogState() }
    ) {

        Column(
            modifier= Modifier
                .size(height = 140.dp, width = 300.dp)

                .background(Color.White),

            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text=value,
                modifier = Modifier
                    .padding(start = 10.dp, bottom = 5.dp)
                    .align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier,
            ){
                TextField(
                    shape = RoundedCornerShape(10.dp),
                    value = productViewModel.selectBrand.value.brand,
                    onValueChange = {
                        productViewModel.updateBrandText(it)

                    },
                    maxLines = 1,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,

                        ),

                    modifier = Modifier.border(width = 1.dp, color = Color.Gray)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,


                ) {
                TextButton(
                    onClick = {
                        kindOfPost()
                        toggleDialogState()
                    },
                    modifier = Modifier
                ) {
                    Text(
                        text = "확인",
                        style = TextStyle(
                            fontStyle = FontStyle.Normal,
                            fontSize = 12.sp
                        ),
                        color= Color.Black
                    )
                }
                TextButton(
                    onClick = {
                        toggleDialogState()
                    },
                    modifier = Modifier
                ) {
                    Text(
                        text = "취소",
                        style = TextStyle(
                            fontStyle = FontStyle.Normal,
                            fontSize = 12.sp
                        ),
                        color= Color.Black
                    )
                }

            }
        }
    }
}


//@Composable
//fun CountComponent(
//    singleItem: ItemData
//) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(
//            text = singleItem.distributor.count.toString(),
//            style = TextStyle(
//                fontStyle = FontStyle.Normal,
//                fontSize = 20.sp
//            ),
//        )
//    }
//}





@Preview(showBackground = true)
@Composable
fun ItemComponentPreview() {
    val testRepository=TestRepository()
    val productViewModel=ProductViewModel(testRepository)

    MaterialTheme {
        ItemComponent(
            ItemData(
                brand = "나이키",
                distributor =
                listOf(
                    Distributor(distributor = "아산 백화점", count = 3),
                    Distributor(distributor = "아산 백화점", count = 3),
                ),

                ),
            productViewModel
            )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ModifyProductPreview() {
    val testRepository=TestRepository()
    val productViewModel=ProductViewModel(testRepository)

    MaterialTheme {
        ModifyOrAddProductDataDialog(productViewModel, toggleDialogState = { productViewModel.toggleShowItemModifyDialog()},kindOfPost = {})
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun AddBrandPreview() {
    val testRepository=TestRepository()
    val productViewModel=ProductViewModel(testRepository)

    MaterialTheme {
        ModifyOrAddBrandDialog(productViewModel, toggleDialogState = { productViewModel.toggleShowItemModifyDialog() }, kindOfPost = {})
    }
}