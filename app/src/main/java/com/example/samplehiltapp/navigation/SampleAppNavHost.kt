package com.example.samplehiltapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun SampleAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "profile"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("profile") {
            /*ProfileScreen(
                onNavigateToFriends = { navController.navigate("friendsList") },
                *//*...*//*
            )*/
        }
        composable("friendslist") { /*FriendsListScreen(*//*...*//*) */}
    }
}