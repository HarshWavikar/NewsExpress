package com.codewithharsh.newsexpress.presentation.search

import androidx.paging.PagingData
import com.codewithharsh.newsexpress.domain.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchState (
    val searchQuery: String = "",
//    val articles : Flow<PagingData<Article>> = emptyFlow()
    val  articles : Flow<PagingData<Article>> ?= null
)