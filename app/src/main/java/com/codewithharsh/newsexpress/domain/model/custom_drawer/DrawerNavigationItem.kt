package com.codewithharsh.newsexpress.domain.model.custom_drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class DrawerNavigationItem(
    val title: String,
    val icon: ImageVector
) {
    Home("Home", Icons.Filled.Home),
    Profile("Profile", Icons.Filled.Person),
    Settings("Settings", Icons.Filled.Settings)
}