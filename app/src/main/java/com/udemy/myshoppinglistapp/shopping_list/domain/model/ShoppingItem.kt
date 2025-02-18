package com.udemy.myshoppinglistapp.shopping_list.domain.model

data class ShoppingItem(
    val id: Int,
    var name: String,
    var quantity: Int,
    var isEditing: Boolean = false,
)

