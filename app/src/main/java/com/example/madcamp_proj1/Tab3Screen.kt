package com.example.madcamp_proj1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.madcamp_proj1.data.Datasource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tab3Screen(modifier: Modifier = Modifier) {

    val images = remember { Datasource().loadGalleryImages() }
    var query by remember { mutableStateOf("") }
    var queryList: MutableList<String> by remember { mutableStateOf(mutableStateListOf()) }

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 4.dp)
            ){
                Text(
                    text = "그룹",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(30))
            ) {
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    placeholder = {
                        Row {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = "Search Icon",
                                tint = Color.Gray
                            )
                            Text(" 검색", color = Color.Gray)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 1.dp, bottom = 1.dp, start = 4.dp, end = 4.dp),
                    shape = RoundedCornerShape(30),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color.LightGray
                    ),
                    singleLine = true
                )
            }

            Divider(
                color = Color.LightGray,
                thickness = 2.dp,
                modifier = Modifier.padding(horizontal = 10.dp))

            val filteredGalleryImageList = images.filter { it ->
                val sortedNameList = it.nameList.sorted()
                val sortedQuery = query.split(",").map { it.trim() }.sorted()
                sortedQuery.all { name -> sortedNameList.contains(name)
                } || it.time.contains(query.trim())
            }
            if(filteredGalleryImageList.size == Datasource().loadGalleryImages().size){
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(queryList) {
                        Text(
                            text = it,
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .fillMaxWidth()
                        )
                    }
                }
            }
            else{
                if(filteredGalleryImageList.isNotEmpty()){
                    if(queryList.contains(query)) queryList.remove(query)
                    queryList.add(0, query)
                }

                GalleryImageGrid(
                    galleryImageList = filteredGalleryImageList,
                    modifier = modifier
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Tab3ScreenPreview() {
    Tab3Screen()
}