package com.udemy.myshoppinglistapp.features.shopping_list.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddShoppingItemDialog(
    onDismissRequest: () -> Unit,
    onConfirm: (String, String) -> Unit,
    onOpenMap: () -> Unit,
) {
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    if (itemName.isBlank()) return@Button
                    if (itemQuantity.isBlank()) itemQuantity = "1"
                    onConfirm(itemName, itemQuantity)
                }) { Text(text = "Add") }
                Button(onClick = { onDismissRequest() }) { Text(text = "Cancel") }
            }

        },
        title = { Text("Add Shopping Item") },
        text = {
            Column {
                OutlinedTextField(
                    value = itemName,
                    onValueChange = { itemName = it },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = itemQuantity, onValueChange = { itemQuantity = it },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Button(onClick = { onOpenMap() }) {
                    Text(text = "Address")
                }
            }
        })

}

@Preview
@Composable
private fun AddShoppingItemDialogPreview() {
    AddShoppingItemDialog({}, { _, _ -> }, {})
}
