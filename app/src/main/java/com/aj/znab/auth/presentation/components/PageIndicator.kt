package com.aj.znab.auth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aj.znab.core.ui.theme.ZnabTheme

@Composable
fun PageIndicator(
    pageSize: Int,
    selectedPageIndex: Int,
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.width(30.dp)
    ) {
        repeat(pageSize) {pageIndex ->
            val isCurrentIndex = pageIndex == selectedPageIndex
            Box(
                modifier = Modifier
                    .size(if (isCurrentIndex) 10.dp else 5.dp)
                    .clip(CircleShape)
                    .background(color = if (isCurrentIndex) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondary)
            )
        }
    }
}

@Preview
@Composable
private fun PageIndicatorPreview() {
    ZnabTheme {
        PageIndicator(modifier = Modifier, pageSize = 3, selectedPageIndex = 2)
    }
}