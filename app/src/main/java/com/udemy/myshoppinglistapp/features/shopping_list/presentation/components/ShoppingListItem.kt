package com.udemy.myshoppinglistapp.features.shopping_list.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.udemy.myshoppinglistapp.features.shopping_list.domain.model.ShoppingItem

@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(2.dp, Color.Black),
                shape = RoundedCornerShape(20)
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f).padding(8.dp)) {
            Row {
                Text(text = item.name, Modifier.padding(horizontal = 16.dp))
                Text(text = "Qty: ${item.quantity}")
            }
            Row(modifier = Modifier.fillMaxWidth().padding(top = 4.dp)) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location",
                    Modifier.padding(horizontal = 16.dp)
                )
                Text(text = item.address)
            }
        }

        IconButton(onClick = { onEditClick() }) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
        }
        IconButton(onClick = { onDeleteClick() }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShoppingListItemPreview() {
    ShoppingListItem(ShoppingItem(1, "Milk", 1), onDeleteClick = { }, onEditClick = {})
}
