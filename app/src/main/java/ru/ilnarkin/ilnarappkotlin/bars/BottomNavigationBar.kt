package ru.ilnarkin.ilnarappkotlin.bars

import androidx.compose.foundation.border
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import ru.ilnarkin.ilnarappkotlin.R
import ru.ilnarkin.ilnarappkotlin.routes.NavRoutes


@Composable
fun BottomNavigationBar (navController: NavController) {

    NavigationBar(
        modifier = Modifier.border(width = 1.dp, color = Color.LightGray),
        containerColor = Color.White,
    ) {
        val colors = NavigationBarItemDefaults.colors(
            selectedIconColor = colorResource(R.color.appPrimaryColor),
            unselectedIconColor = colorResource(R.color.bottomNavColor),
            selectedTextColor = colorResource(R.color.appPrimaryColor),
            unselectedTextColor = colorResource(R.color.bottomNavColor),
            indicatorColor = Color.Transparent
        )
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        if (currentRoute != null) {
            NavigationBarItem(
                selected = currentRoute.contains(NavRoutes.NotesScreen.route),
                colors = colors,
                onClick = {
                    navController.navigate(NavRoutes.NotesScreen.route) {
                        launchSingleTop = false
                        restoreState = true
                    }
                },
                icon = {
                    Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_note),
                        contentDescription = stringResource(R.string.title_notes)
                    )
                },
                label = { Text(stringResource(R.string.title_notes)) }
            )
        }

        NavigationBarItem(
            selected = currentRoute == NavRoutes.TagsScreen.route,
            colors =colors,
            onClick = {
                navController.navigate(NavRoutes.TagsScreen.route) {
                    launchSingleTop = false
                    restoreState = true
                }
            },
            icon = {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_tag),
                    contentDescription = stringResource(R.string.title_tags))
            },
            label = { Text(stringResource(R.string.title_tags)) }
        )

        NavigationBarItem(
            selected = currentRoute == NavRoutes.ArchiveScreen.route,
            colors = colors,
            onClick = {
                navController.navigate(NavRoutes.ArchiveScreen.route) {
                    launchSingleTop = false
                    restoreState = true
                }
            },
            icon = {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_archive),
                    contentDescription = stringResource(R.string.title_archive))
            },
            label = { Text(stringResource(R.string.title_archive)) }
        )

        NavigationBarItem(
            selected = currentRoute == NavRoutes.SearchScreen.route,
            colors = colors,
            onClick = {
                navController.navigate(NavRoutes.SearchScreen.route) {
                    launchSingleTop = false
                    restoreState = true
                }
            },
            icon = {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                    contentDescription = stringResource(R.string.title_search))
            },
            label = { Text(stringResource(R.string.title_search)) }
        )
    }
}