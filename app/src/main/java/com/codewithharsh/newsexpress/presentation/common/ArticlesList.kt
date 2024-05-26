package com.codewithharsh.newsexpress.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.codewithharsh.newsexpress.domain.model.Article
import com.codewithharsh.newsexpress.presentation.Dimens.MediumPadding
import com.codewithharsh.newsexpress.presentation.Dimens.MediumPadding1
import com.codewithharsh.newsexpress.presentation.Dimens.SmallPadding1


// This ArticlesList composable will be used in the search screen and home screen, that's why i have placed this in common package.
@Composable
fun ArticlesList(
    modifier: Modifier,
    articles: LazyPagingItems<Article>,
    onArticleClick: (Article) -> Unit
) {
    val handlePagingResult = handlePagingResult(articles = articles)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.padding(top = 5.dp),
            verticalArrangement = Arrangement.spacedBy(SmallPadding1)
        ) {
            items(count = articles.itemCount) {
                articles[it]?.let { article ->
                    ArticleCard(
                        article = article,
                        onClick = { onArticleClick(article) }
                    )
                }
            }
        }
    }
}


@Composable
fun handlePagingResult(
    articles: LazyPagingItems<Article>,
): Boolean {

    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}


@Composable
fun ShimmerEffect() {
    Column(
        verticalArrangement = Arrangement.spacedBy(MediumPadding1)
    ) {
        repeat(10) {
            ArticleCardShimmerEffect(modifier = Modifier.padding(horizontal = MediumPadding1))
        }
    }
}

