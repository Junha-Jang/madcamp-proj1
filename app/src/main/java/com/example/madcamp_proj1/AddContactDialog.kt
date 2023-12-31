package com.example.madcamp_proj1

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactDialog(
    onDismiss: () -> Unit,
    onComplete: (name: String, number: String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    val isInputValid = remember(name, number) {
        name.isNotBlank() && number.isNotBlank()
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "새로운 연락처 추가", fontSize = 20.sp) },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = "이름") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = name.isBlank()
                )
                OutlinedTextField(
                    value = number,
                    onValueChange = { number = it },
                    label = { Text(text = "전화번호") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = number.isBlank()
                )
            }
        },
        confirmButton = {
            Box {
                Button(
                    onClick = {
                        if (isInputValid) {
                            onComplete(name, number)
                            onDismiss()
                        }
                    },
                    modifier = Modifier
                        .height(12.dp)
                        .align(Alignment.Center)
                        .alpha(0f)
                ) {}
                Text(
                    text = "완료",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        },
        dismissButton = {
            Box {
                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .height(12.dp)
                        .align(Alignment.Center)
                        .alpha(0f)
                ) {}
                Text(
                    text = "취소",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    )
}
