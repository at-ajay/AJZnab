package com.aj.znab.core

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.aj.znab.core.navigation.Znab
import com.aj.znab.core.ui.theme.ZnabTheme
import com.aj.znab.core.util.EventHandler
import com.aj.znab.core.util.UIEvents
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        var shouldShowSplashScreen = true

        /** Splash Screen Configuration **/
        installSplashScreen().apply {
            this.setKeepOnScreenCondition { shouldShowSplashScreen }
            this.setOnExitAnimationListener { screen ->
                ObjectAnimator.ofFloat(screen.iconView, View.SCALE_X, 0.4F, 0.0F).apply {
                    this.interpolator = OvershootInterpolator()
                    this.duration = 1000
                    this.doOnEnd { screen.remove() }
                    this.start()
                }

                ObjectAnimator.ofFloat(screen.iconView, View.SCALE_Y, 0.4F, 0.0F).apply {
                    this.interpolator = OvershootInterpolator()
                    this.duration = 1000
                    this.doOnEnd { screen.remove() }
                    this.start()
                }
            }
        }

        lifecycleScope.launch {
            delay(1000)
            shouldShowSplashScreen = false
        }

        actionBar?.hide()
        setContent {
            ZnabTheme {

                /** UI Events Configuration **/
                val lifecycle = LocalLifecycleOwner.current.lifecycle
                val navController = rememberNavController()

                LaunchedEffect(key1 = lifecycle) {
                    repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                        EventHandler.events.collect { event ->
                            when(event) {
                                is UIEvents.Toast -> {
                                    Toast.makeText(this@MainActivity, event.message, Toast.LENGTH_SHORT).also {
                                        it.setGravity(Gravity.TOP, 0, 0)
                                        it.show()
                                    }
                                }
                                is UIEvents.Navigate -> navController.navigate(event.toScreen.route)
                                is UIEvents.NavigateAndClearBackStack -> navController.navigate(event.toScreen.route) {
                                    popUpTo(event.currentScreen.route) { inclusive = true }
                                }
                            }
                        }
                    }

                }


                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Znab(navController = navController)
                }

            }
        }
    }
}