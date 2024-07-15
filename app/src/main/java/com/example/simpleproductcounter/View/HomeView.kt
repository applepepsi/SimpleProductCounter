package com.example.simpleproductcounter.View

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.simpleproductcounter.component.ItemComponent
import com.example.simpleproductcounter.component.ModifyProductData
import com.example.simpleproductcounter.data.Distributor
import com.example.simpleproductcounter.data.ItemData
import com.example.simpleproductcounter.viewModel.ProductViewModel

@Composable
fun MainView(){

    val productViewModel:ProductViewModel= hiltViewModel()
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text="wdwa"
        )

        LazyColumn(
            modifier = Modifier
                .wrapContentHeight()
                .verticalScroll(scrollState)
        ) {
            item {
                Text(
                    text="wdwa"
                )
            }
            items(items= testData){ singleBrand->
                ItemComponent(
                    singleBrand=singleBrand
                )
            }
        }
    }
}


val testData= listOf(
    ItemData(
        brand = "나이키",
        distributor =
        listOf(
            Distributor(distributor = "아산 백화점", count = 3),
            Distributor(distributor = "아산 백화점", count = 3),
        ),

    ),
    ItemData(
        brand = "아이다스",
        distributor =
        listOf(
            Distributor(distributor = "아산 백화점", count = 3),
            Distributor(distributor = "아산 백화점", count = 3),
        ),

    ),
    ItemData(
        brand = "나이키",
        distributor =
        listOf(
            Distributor(distributor = "아산 백화점", count = 3),
            Distributor(distributor = "아산 백화점", count = 3),
        ),
    ),
    ItemData(
        brand = "아이다스",
        distributor =
        listOf(
            Distributor(distributor = "아산 백화점", count = 3),
            Distributor(distributor = "아산 백화점", count = 3),
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