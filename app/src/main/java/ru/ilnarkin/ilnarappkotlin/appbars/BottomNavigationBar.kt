package ru.ilnarkin.ilnarappkotlin.appbars

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.ilnarkin.ilnarappkotlin.R
import ru.ilnarkin.ilnarappkotlin.routes.NavRoutes


@Composable
fun BottomNavigationBar (navController: NavController) {

    NavigationBar(
        modifier = Modifier.drawBehind {
            var borderStrokeWidth = 2.dp
            val strokeWidthPx = borderStrokeWidth.toPx()
            drawLine(
                color = Color.LightGray,
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = size.width, y = 0f),
                strokeWidth = strokeWidthPx
            )
        },
        containerColor = colorResource(R.color.bottomBarBgColor),
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

        NavigationBarItem(
            selected = currentRoute?.contains(NavRoutes.NotesScreen.route) == true,
            colors = colors,
            onClick = {
                navController.navigate(NavRoutes.NotesScreen.route) {
                    launchSingleTop = true
                    restoreState = false

                    currentRoute?.let {
                        popUpTo(it){
                            saveState = true
                        }
                    }
                }
            },
            icon = {
                Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_note),
                    contentDescription = stringResource(R.string.title_notes)
                )
            },
            label = { Text(stringResource(R.string.title_notes)) }
        )

        NavigationBarItem(
            selected = currentRoute == NavRoutes.TagsScreen.route,
            colors =colors,
            onClick = {
                navController.navigate(NavRoutes.TagsScreen.route) {
                    launchSingleTop = true
                    restoreState = false

                    currentRoute?.let {
                        popUpTo(it){
                            saveState = true
                        }
                    }
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
                    launchSingleTop = true
                    restoreState = false

                    currentRoute?.let {
                        popUpTo(it){
                            saveState = true
                        }
                    }
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
                    launchSingleTop = true
                    restoreState = false

                    currentRoute?.let {
                        popUpTo(it){
                            saveState = true
                        }
                    }
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