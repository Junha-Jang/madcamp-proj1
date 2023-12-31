package com.example.madcamp_proj1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.madcamp_proj1.data.Datasource
import com.example.madcamp_proj1.model.GalleryImage

@Composable
fun Tab2Screen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "갤러리",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Divider(
            color = Color.LightGray,
            thickness = 2.dp,
            modifier = Modifier.padding(horizontal = 2.dp)
        )

        GalleryImageGrid(
            galleryImageList = Datasource().loadGalleryImages(),
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Tab2ScreenPreview() {
    Tab2Screen()
}

@Composable
internal fun GalleryImageGrid(
    galleryImageList: List<GalleryImage>,
    modifier: Modifier = Modifier,
) {
    var colNumber: Int by remember { mutableStateOf(3) }
    var selectedIndex: Int? by remember { mutableStateOf(null) }

    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item { Spacer(modifier = Modifier.size(8.dp)) }
            item {
                for (i in galleryImageList.indices step colNumber) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        for (j in 0 until colNumber) {
                            val imageIndex: Int = i + j
                            val galleryImage: GalleryImage =
                                if (imageIndex < galleryImageList.size) galleryImageList[imageIndex]
                                else GalleryImage(null)
                            GalleryImageCard(
                                galleryImage = galleryImage,
                                colNumber = colNumber,
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable(
                                        onClick = {
                                            if (imageIndex < galleryImageList.size) selectedIndex =
                                                imageIndex
                                        }
                                    )
                            )
                        }
                    }
                }
            }
        }
        Row(
            //verticalAlignment = Alignment.Bottom,
            //horizontalArrangement = Arrangement.End,
            modifier = Modifier
                //.fillMaxSize()
                .padding(end = 8.dp, bottom = 16.dp)
                .align(Alignment.BottomEnd)
        ) {
            ExtendedFloatingActionButton(
                onClick = { if (colNumber > 1) colNumber-- },
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.zoomin),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            ExtendedFloatingActionButton(
                onClick = { if (colNumber < 8) colNumber++ },
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.zoomout),
                    contentDescription = null
                )
            }
        }

        if(selectedIndex != null){
            Box(modifier = Modifier
                .fillMaxSize()
                .alpha(0.5f)
                .background(color = Color.LightGray)
            )
            Box {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    GalleryImageCard(
                        galleryImage = galleryImageList[selectedIndex!!],
                        colNumber = 1,
                        modifier = Modifier
                            .width(500.dp)
                            .height(350.dp)
                            .clickable(onClick = { selectedIndex = null })
                    )
                }
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            if(selectedIndex!! > 0) selectedIndex = selectedIndex!! - 1
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .height(350.dp)
                            .alpha(0f)
                    ) {}
                    Spacer(modifier = Modifier.size(150.dp))
                    Button(
                        onClick = {
                            if(selectedIndex!! != galleryImageList.size - 1) selectedIndex = selectedIndex!! + 1
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .height(350.dp)
                            .alpha(0f)
                    ) {}
                }
            }
        }
    }
}

@Composable
fun GalleryImageCard(
    galleryImage: GalleryImage,
    modifier: Modifier = Modifier,
    colNumber: Int = 1
) {

    Card(modifier = modifier.padding(2.dp)) {
        Column {
            Image(
                painter = painterResource(galleryImage.imageResourceId ?: R.drawable.white),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height((360 / colNumber).dp)
            )
        }
    }
}

@Preview
@Composable
private fun GalleryCardPreview() {
    GalleryImageCard(GalleryImage(R.drawable.image1))
}