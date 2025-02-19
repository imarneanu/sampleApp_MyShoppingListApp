package com.udemy.myshoppinglistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.udemy.myshoppinglistapp.features.location.presentation.LocationViewModel
import com.udemy.myshoppinglistapp.ui.theme.MyShoppingListAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: LocationViewModel = viewModel()

            MyShoppingListAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ShoppingListApp(
                        modifier = Modifier.padding(innerPadding),
                        viewModel,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingListAppPreview() {
    MyShoppingListAppTheme {
        ShoppingListApp(Modifier.background(MaterialTheme.colorScheme.background), viewModel())
    }
}

