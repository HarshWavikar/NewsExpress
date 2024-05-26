package com.codewithharsh.newsexpress.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.codewithharsh.newsexpress.R
import com.codewithharsh.newsexpress.domain.model.Article
import com.codewithharsh.newsexpress.presentation.Dimens.ExtraSmallPadding2
import com.codewithharsh.newsexpress.presentation.Dimens.SmallPadding
import com.codewithharsh.newsexpress.presentation.common.ArticlesList
import com.codewithharsh.newsexpress.presentation.common.SearchBar
import com.codewithharsh.newsexpress.presentation.navgraph.Route

@Composable
fun HomeScreen(article: LazyPagingItems<Article>, navigate: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(R.drawable.news_express_logo),
            contentDescription = "app logo",
            modifier = Modifier
                .padding(start = ExtraSmallPadding2)
                .width(150.dp)
                .height(70.dp)
        )
        Spacer(modifier = Modifier.padding(SmallPadding))

        SearchBar(
            modifier = Modifier.padding(horizontal = SmallPadding),
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = {
                navigate(
                    Route.SearchScreen.route
                )
            },
            onSearch = {}
        )

        Spacer(modifier = Modifier.padding(SmallPadding))

        ArticlesList(
            modifier = Modifier.padding(horizontal = 0.dp),
            articles = article,
            onArticleClick = {
                navigate(Route.DetailScreen.route)
            }
        )
    }
}

