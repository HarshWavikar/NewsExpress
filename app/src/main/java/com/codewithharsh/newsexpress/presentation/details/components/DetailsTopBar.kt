package com.codewithharsh.newsexpress.presentation.details.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.BookmarkAdd
import androidx.compose.material.icons.rounded.FolderShared
import androidx.compose.material.icons.rounded.IosShare
import androidx.compose.material.icons.rounded.OpenInBrowser
import androidx.compose.material.icons.rounded.OpenWith
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.codewithharsh.newsexpress.R
import com.codewithharsh.newsexpress.ui.theme.NewsExpressTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    onBrowsingClicked: () -> Unit,
    onShareClicked: () -> Unit,
    onBookmarkClicked: () -> Unit,
    onBackClicked: () -> Unit,
) {

    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = MaterialTheme.colorScheme.secondary,
            navigationIconContentColor = MaterialTheme.colorScheme.secondary

        ),
        navigationIcon = {
            IconButton(
                onClick = onBackClicked
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIosNew,
                    contentDescription = "Back button icon"
                )
            }
        },
        actions = {
            IconButton(
                onClick = onBookmarkClicked
            ) {
                Icon(
                    imageVector = Icons.Rounded.BookmarkAdd,
                    contentDescription = "Back button icon"
                )
            }
            IconButton(
                onClick = onShareClicked
            ) {
                Icon(
                    imageVector = Icons.Rounded.Share,
                    contentDescription = "Back button icon"
                )
            }
            IconButton(
                onClick = onBrowsingClicked
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_network),
                    contentDescription = "Back button icon"
                )
            }
        }
        ,
        title = {}

    )
}


@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun DetailsTopBarPreview() {
    NewsExpressTheme {
        DetailsTopBar(
            onBrowsingClicked = {},
            onShareClicked = {},
            onBookmarkClicked = {},
            onBackClicked = {}
        )
    }
}