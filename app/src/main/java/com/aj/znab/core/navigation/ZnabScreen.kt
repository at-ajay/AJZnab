package com.aj.znab.core.navigation

sealed class ZnabScreen(val route: String) {
    data object OnBoardingSignInScreen: ZnabScreen("onboarding_sign_in_screen")
    data object DashboardScreen: ZnabScreen("dashboard_screen")
}