package com.codewithharsh.newsexpress.domain.usecases.news

import androidx.paging.PagingData
import com.codewithharsh.newsexpress.domain.model.Article
import com.codewithharsh.newsexpress.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
       return newsRepository.getNews(sources = sources)
    }
}