package com.mobile.fetch.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mobile.fetch.ui.navigation.destination.Items
import com.mobile.fetch.ui.screens.ItemsScreen

@Composable
fun Navigation(
    navHostController: NavHostController, snackbarHostState: SnackbarHostState
) {
    val scope = rememberCoroutineScope()

    NavHost(
        navController = navHostController, startDestination = Items
    ) {

        composable<Items> {
            ItemsScreen(
                snackbarHostState = snackbarHostState,
                scope = scope
            )
        }

    }
}