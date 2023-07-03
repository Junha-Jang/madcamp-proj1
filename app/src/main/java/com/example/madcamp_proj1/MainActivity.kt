package com.example.madcamp_proj1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.madcamp_proj1.ui.theme.Madcamp_proj1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Madcamp_proj1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    var index by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        when(index) {
            1 -> Tab1Screen(modifier = Modifier.weight(1f))
            2 -> Tab2Screen(modifier = Modifier.weight(1f))
            3 -> Tab3Screen(modifier = Modifier.weight(1f))
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            NavButton(
                name = "Tab1",
                onClick = {
                    index = 1
                },
                modifier = Modifier.weight(1f)
            )
            NavButton(
                name = "Tab2",
                onClick = {
                    index = 2
                },
                modifier = Modifier.weight(1f)
            )
            NavButton(
                name = "Tab3",
                onClick = {
                    index = 3
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun NavButton(
    name: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = name
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    Madcamp_proj1Theme {
        App()
    }
}