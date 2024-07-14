package com.example.simpleproductcounter.component

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simpleproductcounter.data.ItemData

@Composable
fun ItemComponent(
    brand: String = "Brand",
    itemDataList: List<ItemData>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,

    ) {

        Text(
            text = brand,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            ),
            modifier = Modifier
                .weight(1f)

        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            items(items = itemDataList) { singleItem ->
                DistributorComponent(singleItem = singleItem)
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items = itemDataList) { singleItem ->
                CountComponent(singleItem = singleItem)
            }
        }
    }
}

@Composable
fun DistributorComponent(
    singleItem: ItemData
) {
    Row(
        modifier = Modifier
            .height(30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = singleItem.distributor,
            style = TextStyle(
                fontStyle = FontStyle.Normal,
                fontSize = 15.sp
            ),
        )
    }
}

@Composable
fun CountComponent(
    singleItem: ItemData
) {
    Row(
        modifier = Modifier
            .height(30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = singleItem.count.toString(),
            style = TextStyle(
                fontStyle = FontStyle.Normal,
                fontSize = 20.sp
            ),
        )
    }
}


val testData= listOf(
    ItemData(),
    ItemData(),
    ItemData(),
    ItemData(),
)

@Preview(showBackground = true)
@Composable
fun ItemComponentPreview() {

    MaterialTheme {
        ItemComponent("Brand",testData)
    }
}