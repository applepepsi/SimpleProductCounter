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
import com.example.simpleproductcounter.ui.theme.Background_Color2
import com.example.simpleproductcounter.viewModel.ProductViewModel

@Composable
fun ItemComponent(
    singleBrand: ItemData,
    productViewModel: ProductViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()

            .padding(bottom = 5.dp)

    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth(),

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp, start = 10.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = singleBrand.brand,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal,
                    ),
                )
            }

            DividerComponent()

            for(i in singleBrand.distributor) {
                DistributorComponent(
                    singleItem = i,
                    deleteItem = {},
                    toggleDialogState = {
                        productViewModel.toggleShowItemModifyDialog()
                    },
                    setSelectDistributor={
                        productViewModel.setSelectItemData(i)
                    }
                )
            }
        }

        TextButton(
            onClick = {
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
            modifier = Modifier.align(Alignment.End)
        )
    }


}

@Composable
fun DistributorComponent(
    singleItem: Distributor,
    setSelectDistributor:()->Unit,
    deleteItem:()->Unit,
    toggleDialogState:()->Unit,
) {



    Row(
        modifier = Modifier
            .fillMaxWidth()

            .clickable {
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

            IconButton(
                onClick = {
                    deleteItem()
                },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            ){
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_baseline_cancle_24),
                    contentDescription = null,
                    modifier = Modifier.size(10.dp),
                )
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

                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,

                    ),

                    modifier = Modifier.weight(1f).border(width = 1.dp, color = Color.Gray)
                )

                Spacer(modifier = Modifier.width(5.dp))

                TextField(
                    shape = RoundedCornerShape(10.dp),
                    value = productViewModel.selectDistributorData.value.count.toString(),
                    onValueChange = {
                        productViewModel.updateModifyCount(it.toInt())
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.weight(1f).border(width = 1.dp, color = Color.Gray)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,

            ) {
                TextButton(
                    onClick = {

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
fun AddBrandDialog(
    productViewModel: ProductViewModel,
    toggleDialogState: () -> Unit,
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
                    .padding(start=10.dp,bottom=5.dp)
                    .align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier,
            ){
                TextField(
                    shape = RoundedCornerShape(10.dp),
                    value = productViewModel.singleBrandText.value,
                    onValueChange = {
                        productViewModel.updateBrandText(it)
                    },

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

                    },
                    modifier = Modifier
                ) {
                    Text(
                        text = "추가",
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
    val productViewModel=ProductViewModel()

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

    val productViewModel=ProductViewModel()

    MaterialTheme {
        ModifyOrAddProductDataDialog(productViewModel, toggleDialogState = { productViewModel.toggleShowItemModifyDialog() })
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun AddBrandPreview() {

    val productViewModel=ProductViewModel()

    MaterialTheme {
        AddBrandDialog(productViewModel, toggleDialogState = { productViewModel.toggleShowItemModifyDialog() })
    }
}