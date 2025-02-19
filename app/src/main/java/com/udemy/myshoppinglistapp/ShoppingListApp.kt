package com.udemy.myshoppinglistapp

import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.udemy.myshoppinglistapp.features.location.domain.LocationUtils
import com.udemy.myshoppinglistapp.features.location.presentation.LocationSelectionScreen
import com.udemy.myshoppinglistapp.features.location.presentation.LocationViewModel
import com.udemy.myshoppinglistapp.features.shopping_list.presentation.ShoppingListScreen

@Composable
fun ShoppingListApp(
    modifier: Modifier = Modifier,
    viewModel: LocationViewModel,
) {
    val context = LocalContext.current
    val locationUtils = LocationUtils(context)

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            if (permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true &&
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
            ) {
                locationUtils.requestLocationUpdates(viewModel)
            } else {
                val rationaleRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) || ActivityCompat.shouldShowRequestPermissionRationale(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )

                if (rationaleRequired) {
                    Toast.makeText(
                        context,
                        "Location permission is required for this feature to work",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "Location permission is required. Please enable it in the app settings.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.ShoppingList.route) {
        composable(Screen.ShoppingList.route) {
            ShoppingListScreen(
                modifier = modifier,
                address = viewModel.addresses.value.firstOrNull()?.address ?: "No Address",
                onOpenMap = {
                    if (locationUtils.hasLocationPermission()) {
                        locationUtils.requestLocationUpdates(viewModel)
                        navController.navigate(route = Screen.Location.route) { this.launchSingleTop }
                    } else {
                        requestPermissionLauncher.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                            )
                        )
                    }
                }
            )
        }
        dialog(Screen.Location.route) {
            viewModel.location.value?.let { currentLocation ->
                LocationSelectionScreen(
                    modifier,
                    location = currentLocation,
                ) { newLocation ->
                    println("current location: $currentLocation")
                    println("new location: $newLocation")
                    viewModel.fetchAddress("${newLocation.latitude},${newLocation.longitude}")
                    println("address from dialog: ${viewModel.addresses.value.firstOrNull()?.address}")
                    navController.popBackStack()
                }
            }
        }
    }

}
