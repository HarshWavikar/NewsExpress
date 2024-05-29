package com.codewithharsh.newsexpress.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.codewithharsh.newsexpress.data.remote.NewsApi
import com.codewithharsh.newsexpress.data.remote.NewsPagingSource
import com.codewithharsh.newsexpress.data.remote.SearchNewsPagingSource
import com.codewithharsh.newsexpress.domain.model.Article
import com.codewithharsh.newsexpress.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow


class NewsRepositoryImpl(
    private val newsApi: NewsApi
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        // Created pager object
        val pager = Pager(
            config = PagingConfig(pageSize = 10), // In each request load 10 articles as response.
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
        return pager
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        val pager = Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    newsApi = newsApi,
                    searchQuery = searchQuery,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
        return pager
    }
}