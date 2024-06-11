package com.aj.znab.auth.presentation.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aj.znab.auth.data.AuthApi
import com.aj.znab.auth.util.GoogleAuthClient
import com.aj.znab.core.navigation.ZnabScreen
import com.aj.znab.core.util.EventHandler
import com.aj.znab.core.util.Task
import com.aj.znab.core.util.UIEvents
import kotlinx.coroutines.launch

class OnBoardSignInViewModal(
    private val googleAuthClient: GoogleAuthClient,
    private val authApi: AuthApi
): ViewModel() {

    var isSignInInitiated by mutableStateOf(false)
        private set

    fun signInUser() = viewModelScope.launch {
        isSignInInitiated = true

        when(val signInResult = googleAuthClient.signIn()) {
            is Task.Success -> {
                authApi.loadInitialData(userId = signInResult.data!!)
                isSignInInitiated = false
                EventHandler.sendEvent(UIEvents.NavigateAndClearBackStack(currentScreen = ZnabScreen.OnBoardingSignInScreen, toScreen = ZnabScreen.DashboardScreen))
            }
            is Task.Failure -> {
                EventHandler.sendEvent(UIEvents.Toast(signInResult.errorMessage!!))
                isSignInInitiated = false
            }
            is Task.InProgress -> Unit
        }
    }

}