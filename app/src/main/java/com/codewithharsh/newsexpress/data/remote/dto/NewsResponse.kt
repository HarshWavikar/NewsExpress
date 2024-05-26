package com.codewithharsh.newsexpress.data.remote.dto

import com.codewithharsh.newsexpress.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)

