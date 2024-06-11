package com.aj.znab.auth.presentation.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.aj.znab.R

data class OnBoardPage(
    @StringRes val pageTitle: Int,
    @StringRes val pageDescription: Int,
    @DrawableRes val pageImg: Int
)

val onBoardPages = listOf(
    OnBoardPage(
        pageTitle = R.string.title_onboard_page_one,
        pageDescription = R.string.description_onboard_page_one,
        pageImg = R.drawable.img_onboard_money
    ),
    OnBoardPage(
        pageTitle = R.string.title_onboard_page_two,
        pageDescription = R.string.description_onboard_page_two,
        pageImg = R.drawable.img_onboard_stats
    ),
    OnBoardPage(
        pageTitle = R.string.title_onboard_page_three,
        pageDescription = R.string.description_onboard_page_three,
        pageImg = R.drawable.img_onboard_overview
    )
)
