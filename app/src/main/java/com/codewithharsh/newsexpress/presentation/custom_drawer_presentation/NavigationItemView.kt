package com.codewithharsh.newsexpress.presentation.custom_drawer_presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codewithharsh.newsexpress.domain.model.custom_drawer.DrawerNavigationItem

@Composable
fun NavigationItemView(
    drawerNavigationItem: DrawerNavigationItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
//            .padding(5.dp)
            .fillMaxWidth()
//            .padding(5.dp)
            .clip(RoundedCornerShape(size = 99.dp))
            .clickable { onClick() }
            .background(
                color = if (selected)
                    MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp)
                else
                    Color.Unspecified,
                shape = RoundedCornerShape(size = 99.dp),
            )
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = drawerNavigationItem.icon,
            contentDescription = null,
            tint = if (selected)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = drawerNavigationItem.title,
            style = MaterialTheme.typography.bodyLarge,
            color = if (selected)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onSurface,
            fontWeight = if (selected)
                FontWeight.Bold
            else
                FontWeight.Normal,
            lineHeight = 20.sp
        )
    }
}