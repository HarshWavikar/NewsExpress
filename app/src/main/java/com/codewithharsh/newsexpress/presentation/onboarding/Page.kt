package com.codewithharsh.newsexpress.presentation.onboarding

import androidx.annotation.DrawableRes
import com.codewithharsh.newsexpress.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)


val pages = listOf(
    Page(
        title = "Global News at your fingertips",
        description = "Get the latest headlines and in-depth coverage from around the world with News Express App.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Stay Updated with the latest news",
        description = "Our app brings you breaking news, top stories, and expert analysis on politics," +
                " business, technology, sports, entertainment, and more.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Breaking News Alerts",
        description = "Never miss a moment with our Breaking News Alerts. " +
                "Stay instantly informed about the most important events happening around the world.",
        image = R.drawable.onboarding3
    )
)