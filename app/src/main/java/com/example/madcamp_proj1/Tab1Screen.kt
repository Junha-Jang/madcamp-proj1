package com.example.madcamp_proj1

import EditContactDialog
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

data class Contact(
    val name: String,
    val number: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tab1Screen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var contacts by remember { mutableStateOf(readContacts(context)) }
    var query by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var selectedContact by remember { mutableStateOf(Contact("", "")) }
    var showContactDialog by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = modifier) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
            ){
                Text(
                    text = "연락처",
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
                            Icon(Icons.Default.Search, contentDescription = "Search Icon", tint = Color.Gray)
                            Text(" 검색", color = Color.Gray)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 1.dp, bottom = 1.dp, start = 4.dp, end = 4.dp),
                    textStyle = TextStyle(color = Color.Black),
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

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(contacts.filter {
                    it.name.contains(query, ignoreCase = true) ||
                    it.number.contains(query, ignoreCase = true)
                }) { contact ->
                    Text(
                        text = contact.name,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .fillMaxWidth()
                            .clickable {
                                selectedContact = contact
                                showContactDialog = true
                            },
                        color = Color.DarkGray
                    )
                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 10.dp))
                }
            }

            if (showDialog) {
                AddContactDialog(
                    onDismiss = { showDialog = false },
                    onComplete = { name, number ->
                        val newContact = Contact(name, number)
                        contacts = (contacts + newContact)
                        contacts.sortedBy { it.name }
                        saveContacts(context, contacts)
                        showDialog = false
                    }
                )
            }

            if (showContactDialog) {
                EditContactDialog(
                    contact = selectedContact,
                    onDismiss = { showContactDialog = false },
                    onEdit = { name, number ->
                        contacts = contacts.filter { it != selectedContact }
                        selectedContact = Contact(name, number)
                        contacts = (contacts + selectedContact)
                        contacts.sortedBy { it.name }
                        saveContacts(context, contacts)
                        showContactDialog = false
                    },
                    onDelete = {
                        contacts = contacts.filter { it != selectedContact }
                        contacts.sortedBy { it.name }
                        saveContacts(context, contacts)
                        showContactDialog = false
                    }
                )
            }
        }

        FloatingActionButton(
            onClick = { showDialog = true },
            modifier = Modifier
                .padding(16.dp)
                .width(60.dp)
                .height(60.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                painter = painterResource(R.drawable.plus),
                contentDescription = null,
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}




private fun saveContacts(context: Context, contacts: List<Contact>) {
    val gson = Gson()
    val json = gson.toJson(contacts)
    context.openFileOutput("contacts.json", Context.MODE_PRIVATE).use {
        it.write(json.toByteArray())
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
    val contacts: List<Contact> = gson.fromJson(json, type)
    contacts.sortedBy { it.name }
    return gson.fromJson(json, type)
}
/*
private fun readContacts(context: Context): List<Contact> {
    val gson = Gson()
    val json: String
    try {
        val inputStream = context.openFileInput("contacts.json")
        json = inputStream.bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return emptyList()
    }
    val type = object : TypeToken<List<Contact>>() {}.type
    val contacts: MutableList<Contact> = gson.fromJson(json, type)
    contacts.sortedBy { it.name }
    return contacts
}*/

@Preview(showBackground = true)
@Composable
fun Tab1ScreenPreview() {
    Tab1Screen()
}
