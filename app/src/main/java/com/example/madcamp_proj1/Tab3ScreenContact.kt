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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.madcamp_proj1.data.Datasource
import com.example.madcamp_proj1.model.GalleryImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tab3ScreenContact(modifier: Modifier = Modifier) {

    val images = remember { Datasource().loadGalleryImages() }
    var query by remember { mutableStateOf("") }

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {

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

            Divider(color = Color.LightGray, thickness = 1.dp)
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(images.filter {
                    val sortedNameList = it.nameList.sorted()
                    val sortedQuery = query.split(",").map { it.trim() }.sorted()
                    sortedQuery.any { name -> sortedNameList.contains(name)
                    } || it.time.contains(query.trim())
                }) { image ->
                    ImageCard(image = image)
                }
            }
        }
    }
}

@Composable
fun ImageCard(image: GalleryImage) {
    Column {
        Text(
            text = "Resource ID: ${image.imageResourceId}",
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = image.nameList.sorted().joinToString(),
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = image.time,
            modifier = Modifier.padding(8.dp)
        )
    }
}

