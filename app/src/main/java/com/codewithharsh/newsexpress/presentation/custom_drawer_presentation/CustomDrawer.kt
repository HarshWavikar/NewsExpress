package com.codewithharsh.newsexpress.presentation.custom_drawer_presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.codewithharsh.newsexpress.R
import com.codewithharsh.newsexpress.domain.model.custom_drawer.DrawerNavigationItem

@Composable
fun CustomDrawer(
    selectedNavigationDrawerItem: DrawerNavigationItem,
    onDrawerNavigationItemClick: (DrawerNavigationItem) -> Unit,
    onCloseClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.6f)
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = onCloseClick
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Close Drawer",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.news_express_logo),
            contentDescription = "App logo image"
        )
        Spacer(modifier = Modifier.height(40.dp))
        DrawerNavigationItem.entries.toTypedArray().take(2).forEach { drawerNavigationItem ->
            NavigationItemView(
                drawerNavigationItem = drawerNavigationItem,
                selected = drawerNavigationItem == selectedNavigationDrawerItem,
                onClick = { onDrawerNavigationItemClick(drawerNavigationItem) }
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
//        Spacer(modifier = Modifier.height(80.dp))
        Spacer(modifier = Modifier.weight(1f))
        DrawerNavigationItem.entries.toTypedArray().takeLast(1).forEach { drawerNavigationItem ->
            NavigationItemView(
                drawerNavigationItem = drawerNavigationItem,
                selected = false,
                onClick = {
                    when (drawerNavigationItem) {
                        DrawerNavigationItem.Settings -> {
                            onDrawerNavigationItemClick(DrawerNavigationItem.Settings)
                        }

                        else -> {}
                    }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
//        DrawerNavigationItem.entries.toTypedArray().take(1).forEach { drawerNavigationItem ->
//            NavigationItemView(
//                drawerNavigationItem = drawerNavigationItem,
//                selected = false,
//                onClick = {
//                    when (drawerNavigationItem) {
//                        DrawerNavigationItem.Settings -> {
//                            onDrawerNavigationItemClick(DrawerNavigationItem.Settings)
//                        }
//
//                        else -> {}
//                    }
//                }
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//        }
    }
}