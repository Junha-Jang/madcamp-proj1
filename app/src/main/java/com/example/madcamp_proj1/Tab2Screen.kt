package com.example.madcamp_proj1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.madcamp_proj1.data.Datasource
import com.example.madcamp_proj1.model.GalleryImage

@Composable
fun Tab2Screen(modifier: Modifier = Modifier) {
    /*
    Text(
        text = "This is Tab2",
        modifier = modifier
    )
    LazyColumn {
        item { ImageRow() }
        item { ImageRow() }
        item { ImageRow() }
        item { ImageRow() }
        item { ImageRow() }
        item { ImageRow() }
        item { ImageRow() }
    }
    */

    GalleryImageList(
        galleryImageList = Datasource().loadGalleryImages(),
        modifier = modifier
    )
}
/*
@Composable
fun ImageRow(modifier: Modifier = Modifier) {
    Row {
        ImageCard(
            imageResourceId = R.drawable.ic_task_completed,
            contentDescription = "wow",
            modifier = Modifier.weight(1f)
        )
        ImageCard(
            imageResourceId = R.drawable.ic_task_completed,
            contentDescription = "wow",
            modifier = Modifier.weight(1f)
        )
        ImageCard(
            imageResourceId = R.drawable.ic_task_completed,
            contentDescription = "wow",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ImageCard(
    imageResourceId: Int,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.padding(8.dp)) {
        Image(
            painter = painterResource(imageResourceId),
            contentDescription = contentDescription,
            modifier = modifier.padding(8.dp),
            contentScale = ContentScale.Crop
        )
    }
}
*/
@Preview(showBackground = true)
@Composable
fun Tab2ScreenPreview() {
    Tab2Screen()
}

@Composable
fun GalleryImageCard(galleryImage: GalleryImage, modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding(2.dp)) {
        Column {
            Image(
                painter = painterResource(galleryImage.imageResourceId),
                contentDescription = null,//stringResource(galleryImage.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                contentScale = ContentScale.Crop
            )
            /*
            Text(
                text = stringResource(affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            */
        }
    }
}

@Composable
private fun GalleryImageList(galleryImageList: List<GalleryImage>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(galleryImageList){ galleryImage ->
            Row(modifier = Modifier.fillMaxWidth()) {
                GalleryImageCard(
                    galleryImage = galleryImage,
                    modifier = Modifier.weight(1f)
                )
                GalleryImageCard(
                    galleryImage = galleryImage,
                    modifier = Modifier.weight(1f)
                )
                GalleryImageCard(
                    galleryImage = galleryImage,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview
@Composable
private fun GalleryCardPreview() {
    GalleryImageCard(GalleryImage(R.drawable.image1))
}