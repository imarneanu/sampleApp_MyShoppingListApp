package com.udemy.myshoppinglistapp

sealed class Screen(val route: String) {
    data object ShoppingList : Screen("shopping_list_screen")
    data object Location : Screen("location_screen")
}
