package com.aj.znab.auth.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.aj.znab.auth.data.AuthApi
import com.aj.znab.auth.presentation.components.GoogleAuthButton
import com.aj.znab.auth.presentation.components.OnBoardingScreen
import com.aj.znab.auth.presentation.components.PageIndicator
import com.aj.znab.auth.presentation.util.onBoardPages
import com.aj.znab.auth.util.GoogleAuthClient
import com.aj.znab.core.ui.theme.ZnabTheme

@Composable
fun OnBoardSignInScreen(vm: OnBoardSignInViewModal) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val (onboardPager, pageIndicator, authBtn) = createRefs()
        val pagerState = rememberPagerState(initialPage = 0) { onBoardPages.size }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.constrainAs(onboardPager) {
                top.linkTo(parent.top, 20.dp)
                start.linkTo(parent.start, 20.dp)
                end.linkTo(parent.end, 20.dp)
                bottom.linkTo(parent.bottom, 20.dp)

                width = Dimension.fillToConstraints
            }
        ) {index ->
            OnBoardingScreen(page = onBoardPages[index])
        }
        
        PageIndicator(
            pageSize = onBoardPages.size,
            selectedPageIndex = pagerState.currentPage,
            modifier = Modifier.constrainAs(pageIndicator) {
                top.linkTo(onboardPager.bottom, 30.dp)
                start.linkTo(parent.start, 20.dp)
                end.linkTo(parent.end, 20.dp)
            }
        )

        AnimatedVisibility(
            visible = pagerState.currentPage == 2,
            enter = scaleIn(animationSpec = tween(durationMillis = 1000)) + fadeIn(),
            exit = scaleOut(animationSpec = tween(durationMillis = 1000)) + fadeOut(),
            modifier = Modifier.constrainAs(authBtn) {
                start.linkTo(parent.start, 20.dp)
                end.linkTo(parent.end, 20.dp)
                bottom.linkTo(parent.bottom, 60.dp)

                width = Dimension.fillToConstraints
            }
        ) {
            GoogleAuthButton(isSignInInitiated = vm.isSignInInitiated) { vm.signInUser() }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun OnBoardSignInScreenPreview() {
    ZnabTheme {
        OnBoardSignInScreen(
            vm = OnBoardSignInViewModal(
                googleAuthClient = GoogleAuthClient(context = LocalContext.current),
                authApi = AuthApi()
            )
        )
    }
}