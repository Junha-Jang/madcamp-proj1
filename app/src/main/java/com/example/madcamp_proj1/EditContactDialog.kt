import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.madcamp_proj1.Contact

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditContactDialog(
    contact: Contact,
    onDismiss: () -> Unit,
    onEdit: (name: String, number: String) -> Unit,
    onDelete: () -> Unit
) {
    var name by remember { mutableStateOf(contact.name) }
    var number by remember { mutableStateOf(contact.number) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "연락처 수정", fontSize = 24.sp) },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = "이름") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = number,
                    onValueChange = { number = it },
                    label = { Text(text = "전화번호") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onEdit(name, number) }
            ) {
                Text(text = "완료")
            }
        },
        dismissButton = {
            Button(
                onClick = { onDelete() }
            ) {
                Text(text = "삭제")
            }
        }
    )
}