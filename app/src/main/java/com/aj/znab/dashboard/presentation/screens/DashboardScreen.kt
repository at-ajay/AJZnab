package com.aj.znab.dashboard.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.aj.znab.auth.util.GoogleAuthClient
import com.aj.znab.core.ui.theme.ZnabTheme
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen() {

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)) {

        val (label) = createRefs()

        Button(onClick = {
                        coroutineScope.launch {
                            GoogleAuthClient(context).signOut()
                        }
        }, modifier = Modifier.constrainAs(label) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }) {
            Text(text = "SignOut")
        }

    }

}

@Preview
@Composable
private fun DashboardScreenPreview() {
    ZnabTheme {
        DashboardScreen()
    }
}