package com.codewithharsh.newsexpress.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.paging.compose.collectAsLazyPagingItems
import com.codewithharsh.newsexpress.presentation.Dimens.MediumPadding1
import com.codewithharsh.newsexpress.presentation.Dimens.SmallPadding
import com.codewithharsh.newsexpress.presentation.common.ArticlesList
import com.codewithharsh.newsexpress.presentation.common.SearchBar
import com.codewithharsh.newsexpress.presentation.navgraph.Route
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigate: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        coroutineScope.launch {
            keyboardController?.show()
        }
        onDispose { }
    }
    Column(
        modifier = Modifier
            .padding(
            top = MediumPadding1,
            start = SmallPadding,
            end = SmallPadding
        )
            .statusBarsPadding()
    ) {
        SearchBar(text = state.searchQuery, readOnly = false, onValueChange = {event(SearchEvent.UpdateSearchQuery(it))} , onSearch = {event(SearchEvent.SearchNews)})
        Spacer(modifier = Modifier.height(MediumPadding1))
        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(articles = articles, onArticleClick = { navigate(Route.DetailScreen.route)})
        }
    }
}