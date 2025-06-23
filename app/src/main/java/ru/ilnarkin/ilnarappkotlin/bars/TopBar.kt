package ru.ilnarkin.ilnarappkotlin.bars


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.ilnarkin.ilnarappkotlin.R
import ru.ilnarkin.ilnarappkotlin.routes.NavRoutes


@ExperimentalMaterial3Api
@Composable
fun TopBar(navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navBackStackEntry?.destination?.route

    val title = when(route){
        NavRoutes.NotesScreen.route -> stringResource(R.string.title_notes)
        NavRoutes.TagsScreen.route -> stringResource(R.string.title_tags)
        NavRoutes.ArchiveScreen.route -> stringResource(R.string.title_archive)
        NavRoutes.SearchScreen.route -> stringResource(R.string.title_search)
        NavRoutes.SettingsScreen.route -> stringResource(R.string.title_settings)
        else -> stringResource(R.string.app_name)
    }

    val backButtonVisible = route != NavRoutes.NotesScreen.route
            && route != NavRoutes.TagsScreen.route && route != NavRoutes.ArchiveScreen.route
            && route != NavRoutes.SearchScreen.route

    TopAppBar(
        title = { Text(text= title, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.appPrimaryColor),
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White
        ),

        navigationIcon = {
            if (backButtonVisible){
                IconButton(onClick = {navController.navigateUp()}) { Icon(painter = painterResource(R.drawable.ic_arrow_left),
                    contentDescription = "Меню")}
            }
            else null
        },


        actions={
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.White
                ),
                onClick = {navController.navigate(NavRoutes.SettingsScreen.route)}) { Icon(painter = painterResource(R.drawable.ic_settings),
                contentDescription = "Настройки")}

            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.White
                ),
                onClick = {}) { Icon(painter = painterResource(R.drawable.ic_logout),
                contentDescription = "Выйти")}
        }
    )
}