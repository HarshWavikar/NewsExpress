package com.codewithharsh.newsexpress.presentation.details

sealed class DetailsEvent {
    data object SaveArticle : DetailsEvent()
}