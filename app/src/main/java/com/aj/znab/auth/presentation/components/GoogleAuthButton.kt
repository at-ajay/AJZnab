package com.aj.znab.auth.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.aj.znab.R
import com.aj.znab.core.ui.theme.ZnabTheme

@Composable
fun GoogleAuthButton(
    isSignInInitiated: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.height(55.dp).fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surface,
        onClick = onClick
    ) {
        ConstraintLayout {
            val (leadingIcon, label) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = null,
                modifier = Modifier.constrainAs(leadingIcon) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, 30.dp)
                    bottom.linkTo(parent.bottom)
                }
            )

            Text(
                text = stringResource(id = if (isSignInInitiated) R.string.google_btn_loading_msg else  R.string.google_btn_label),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.constrainAs(label) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, 20.dp)
                    end.linkTo(parent.end, 20.dp)
                    bottom.linkTo(parent.bottom)
                }
            )
        }
    }
}

@Preview
@Composable
private fun GoogleAuthButtonPreview() {
    ZnabTheme {
        GoogleAuthButton(isSignInInitiated = false) {}
    }
}