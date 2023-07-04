package com.example.madcamp_proj1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    var tabIndex by remember { mutableStateOf(1) }
    val tabName: Array<String> = arrayOf("", "Contact", "Gallery", "Group")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        when(tabIndex) {
            1 -> Tab1Screen(modifier = Modifier.fillMaxSize().weight(1f))
            2 -> Tab2Screen(modifier = Modifier.fillMaxSize().weight(1f))
            3 -> Tab3Screen(modifier = Modifier.fillMaxSize().weight(1f))
        }
        Spacer(modifier = Modifier.size(6.dp))
        Row {
            Spacer(modifier = Modifier.size(6.dp))
            for(i in 1..3) {
                NavButton(
                    name = tabName[i],
                    activated = tabIndex == i,
                    onClick = {
                        tabIndex = i
                    },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.size(6.dp))
            }
        }
        Spacer(modifier = Modifier.size(6.dp))
    }
}

@Composable
fun NavButton(
    name: String,
    activated: Boolean,
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