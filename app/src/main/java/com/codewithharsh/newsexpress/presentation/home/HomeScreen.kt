package com.codewithharsh.newsexpress.presentation.home

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.codewithharsh.newsexpress.core.coloredShadow
import com.codewithharsh.newsexpress.domain.model.Article
import com.codewithharsh.newsexpress.domain.model.custom_drawer.CustomDrawerState
import com.codewithharsh.newsexpress.domain.model.custom_drawer.DrawerNavigationItem
import com.codewithharsh.newsexpress.domain.model.custom_drawer.isOpened
import com.codewithharsh.newsexpress.domain.model.custom_drawer.opposite
import com.codewithharsh.newsexpress.presentation.Dimens.SmallPadding
import com.codewithharsh.newsexpress.presentation.Dimens.SmallPadding1
import com.codewithharsh.newsexpress.presentation.Dimens.VerySmallPadding
import com.codewithharsh.newsexpress.presentation.common.ArticlesList
import com.codewithharsh.newsexpress.presentation.common.SearchBar
import com.codewithharsh.newsexpress.presentation.custom_drawer_presentation.CustomDrawer
import com.codewithharsh.newsexpress.presentation.navgraph.Route
import kotlin.math.roundToInt

@Composable
fun HomeScreen(article: LazyPagingItems<Article>, navigate: (String) -> Unit) {

    var drawerState by remember { mutableStateOf(CustomDrawerState.Closed) }
    var selectedNavigationItem by remember { mutableStateOf(DrawerNavigationItem.Home) }

    val configuration = LocalConfiguration.current
    val density = LocalDensity.current.density

    val screenWidth = remember {
        derivedStateOf { (configuration.screenWidthDp * density).roundToInt() }
    }
    val offsetValue by remember{ derivedStateOf { (screenWidth.value /4.5).dp } }
    val animatedOffset by animateDpAsState(
        targetValue = if (drawerState.isOpened()) offsetValue else 0.dp,
        label = "Animated Offset"
    )
    val animatedScale by animateFloatAsState(
        targetValue = if (drawerState.isOpened()) 0.9f else 1f,
        label = "Animated Scale"
    )

    // If drawer state is opened while we press the back button then we close it immediately
    BackHandler(enabled = drawerState.isOpened()) {
        drawerState = CustomDrawerState.Closed
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize()
    ) {
        CustomDrawer(
            selectedNavigationDrawerItem = selectedNavigationItem,
            onDrawerNavigationItemClick = {
                selectedNavigationItem = it
                drawerState = CustomDrawerState.Closed
            },
            onCloseClick = {drawerState = CustomDrawerState.Closed}
        )
        HomeContent(
            modifier = Modifier
                .offset (x = animatedOffset)
                .scale(scale = animatedScale)
                .coloredShadow(
                    color = Color.Black,
                    alpha = 0.1f,
                    shadowRadius = 50.dp,

                ),
            articles = article,
            navigate = navigate,
            drawerState = drawerState,
            onDrawerClicked = { drawerState = it}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    navigate: (String) -> Unit,
    drawerState: CustomDrawerState,
    onDrawerClicked: (CustomDrawerState) -> Unit
){
    Scaffold (
        modifier = modifier.clickable(enabled = drawerState == CustomDrawerState.Opened){
            onDrawerClicked(CustomDrawerState.Closed)
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Home")
                },
                navigationIcon = {
                    IconButton(onClick = { onDrawerClicked(drawerState.opposite()) }){
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu Icon"
                        )
                    }
                }
            )
        }
    ){
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(top = 10.dp)
                .statusBarsPadding()
        ) {
//            Image(
//                painter = painterResource(R.drawable.news_express_logo),
//                contentDescription = "app logo",
//                modifier = Modifier
//                    .padding(start = ExtraSmallPadding2)
//                    .width(150.dp)
//                    .height(70.dp)
//            )
//            Spacer(modifier = Modifier.padding(SmallPadding))

            SearchBar(
                modifier = Modifier.padding(horizontal = SmallPadding1),
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

            Spacer(modifier = Modifier.padding(VerySmallPadding))

            ArticlesList(
                modifier = Modifier.padding(horizontal = 5.dp).padding(top = 10.dp),
                articles = articles,
                onArticleClick = {
                    navigate(Route.DetailScreen.route)
                }
            )
        }
    }
}

