package com.codewithharsh.newsexpress.presentation.onboarding

sealed class OnBoardingEvent{
    data object saveAppEntryEvent : OnBoardingEvent()
}
