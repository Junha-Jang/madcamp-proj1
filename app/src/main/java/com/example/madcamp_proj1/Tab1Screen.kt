package com.example.madcamp_proj1

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

data class Contact(
    val name: String,
    val number: String
)

@Composable
fun Tab1Screen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val contacts = readContacts(context)

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "연락처",
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )
        LazyColumn {
            items(contacts) { contact ->
                Text(
                    text = contact.name,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth()
                )
            }
        }
        FloatingActionButton(
            onClick = { /* TODO: Navigate to AddContactScreen */ },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.End)
        ) {
            Icon(Icons.Filled.Add, contentDescription = null)
        }
    }
}

private fun readContacts(context: Context): List<Contact> {
    val gson = Gson()
    val json: String
    try {
        val inputStream = context.resources.openRawResource(R.raw.contacts)
        json = inputStream.bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return emptyList()
    }
    val type = object : TypeToken<List<Contact>>() {}.type
    return gson.fromJson(json, type)
}

@Preview(showBackground = true)
@Composable
fun Tab1ScreenPreview() {
    Tab1Screen()
}
