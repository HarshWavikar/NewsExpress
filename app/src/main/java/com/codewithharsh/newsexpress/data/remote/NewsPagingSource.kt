package com.codewithharsh.newsexpress.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.codewithharsh.newsexpress.core.Constants.API_KEY
import com.codewithharsh.newsexpress.domain.model.Article

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val sources: String
) : PagingSource<Int, Article>() {

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        // Here we gonna make request tot the api to get the data
        val page = params.key ?: 1
        return try {
            val newsResponse = newsApi.getNews(sources = sources, page = page, apiKey = API_KEY)
            totalNewsCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title }    // Remove duplicates
            LoadResult.Page(
                data = articles,
//                prevKey = if (page == 1) null else page - 1,
                prevKey = null,
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)

        }
    }
}