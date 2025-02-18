package com.udemy.myshoppinglistapp.shopping_list.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.udemy.myshoppinglistapp.shopping_list.domain.model.ShoppingItem
import com.udemy.myshoppinglistapp.shopping_list.presentation.components.AddShoppingItemDialog
import com.udemy.myshoppinglistapp.shopping_list.presentation.components.ShoppingItemEditor
import com.udemy.myshoppinglistapp.shopping_list.presentation.components.ShoppingListItem

@Composable
fun ShoppingListScreen(modifier: Modifier = Modifier) {
    var shoppingItems by remember {
        mutableStateOf(
            listOf(
                ShoppingItem(
                    1,
                    "Milk",
                    1,
                    false
                )
            )
        )
    }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Add Item")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(shoppingItems) { item ->
                if (item.isEditing) {
                    ShoppingItemEditor(item, onEditComplete = { name, quantity ->
                        shoppingItems = shoppingItems.map { it.copy(isEditing = false) }
                        val editedItem = shoppingItems.find { it.id == item.id }
                        editedItem?.let {
                            it.name = name
                            it.quantity = quantity
                        }
                    })
                } else {
                    ShoppingListItem(item, onEditClick = {
                        shoppingItems = shoppingItems.map {
                            it.copy(isEditing = it.id == item.id)
                        }
                    }, onDeleteClick = {
                        shoppingItems -= item
                    })
                }
            }
        }
    }

    if (showDialog) {
        AddShoppingItemDialog(
            onDismissRequest = { showDialog = false },
            onConfirm = { itemName, itemQuantity ->
                val newItem = ShoppingItem(
                    id = shoppingItems.size + 1,
                    name = itemName,
                    quantity = itemQuantity.toInt(),
                )
                shoppingItems += newItem
                showDialog = false
            }
        )
    }
}
