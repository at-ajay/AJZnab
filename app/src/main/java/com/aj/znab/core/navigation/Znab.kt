package com.aj.znab.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aj.znab.auth.data.AuthApi
import com.aj.znab.auth.presentation.screens.OnBoardSignInScreen
import com.aj.znab.auth.presentation.screens.OnBoardSignInViewModal
import com.aj.znab.auth.util.GoogleAuthClient
import com.aj.znab.core.App
import com.aj.znab.core.util.viewModalFactory
import com.aj.znab.dashboard.presentation.screens.DashboardScreen

@Composable
fun Znab(navController: NavHostController) {
    val startDestination = if (App.auth.currentUser != null) ZnabScreen.DashboardScreen.route else ZnabScreen.OnBoardingSignInScreen.route

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = ZnabScreen.OnBoardingSignInScreen.route) {
            val context = LocalContext.current
            val onBoardSignInViewModal = viewModel<OnBoardSignInViewModal>(
                factory = viewModalFactory {
                    OnBoardSignInViewModal(
                        googleAuthClient = GoogleAuthClient(context = context),
                        authApi = AuthApi()
                    )
                }
            )
            OnBoardSignInScreen(vm = onBoardSignInViewModal)
        }

        composable(route = ZnabScreen.DashboardScreen.route) {
            DashboardScreen()
        }
    }
}