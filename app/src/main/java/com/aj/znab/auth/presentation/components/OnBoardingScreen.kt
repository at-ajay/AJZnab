package com.aj.znab.auth.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.aj.znab.R
import com.aj.znab.auth.presentation.util.OnBoardPage
import com.aj.znab.auth.presentation.util.onBoardPages
import com.aj.znab.core.ui.theme.ZnabTheme

@Composable
fun OnBoardingScreen(page: OnBoardPage) {

    ConstraintLayout(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background)) {
        val (img, title, description) = createRefs()

        Image(
            painter = painterResource(id = page.pageImg),
            contentDescription = null,
            contentScale = if (page.pageImg == R.drawable.img_onboard_money) ContentScale.Inside else ContentScale.Fit,
            modifier = Modifier.constrainAs(img) {
                top.linkTo(parent.top, 30.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

                width = Dimension.fillToConstraints
                height = Dimension.value(300.dp)
            }
        )

        Text(
            text = stringResource(id = page.pageTitle),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(img.bottom, 40.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

                width = Dimension.fillToConstraints
            }
        )

        Text(
            text = stringResource(id = page.pageDescription),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            textAlign = TextAlign.Center,
            modifier = Modifier.constrainAs(description) {
                top.linkTo(title.bottom, 10.dp)
                start.linkTo(title.start)
                end.linkTo(title.end)
                bottom.linkTo(parent.bottom, 30.dp)

                width = Dimension.fillToConstraints
            }
        )
    }

}

@Preview
@Composable
fun OnBoardingScreenPreview() {
    ZnabTheme {
        OnBoardingScreen(page = onBoardPages[2])
    }
}