package ru.ilnarkin.ilnarappkotlin

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.ilnarkin.ilnarappkotlin.appbars.BottomNavigationBar
import ru.ilnarkin.ilnarappkotlin.appbars.TopBar
import ru.ilnarkin.ilnarappkotlin.routes.NavRoutes
import ru.ilnarkin.ilnarappkotlin.screens.ArchiveScreen
import ru.ilnarkin.ilnarappkotlin.screens.NoteAddScreen
import ru.ilnarkin.ilnarappkotlin.screens.NoteEditScreen
import ru.ilnarkin.ilnarappkotlin.screens.NoteViewScreen
import ru.ilnarkin.ilnarappkotlin.screens.NotesScreen
import ru.ilnarkin.ilnarappkotlin.screens.SearchScreen
import ru.ilnarkin.ilnarappkotlin.screens.SettingsScreen
import ru.ilnarkin.ilnarappkotlin.screens.TagsScreen


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column(Modifier.fillMaxSize().statusBarsPadding().navigationBarsPadding()) {
                Main()
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Main() {
    val navController = rememberNavController()

    Column(modifier = Modifier.displayCutoutPadding()) {
        TopBar(navController)
        NavHost(navController, startDestination = NavRoutes.NotesScreen.route, modifier = Modifier.weight(1f)) {
            composable(NavRoutes.NotesScreen.route) { NotesScreen(navController = navController) }
            composable(NavRoutes.NoteAddScreen.route) { NoteAddScreen() }
            composable(NavRoutes.NoteEditScreen.route) { NoteEditScreen() }
            composable(NavRoutes.NoteViewScreen.route) { NoteViewScreen() }
            composable(NavRoutes.TagsScreen.route) { TagsScreen() }
            composable(NavRoutes.ArchiveScreen.route) { ArchiveScreen() }
            composable(NavRoutes.SearchScreen.route) { SearchScreen(navController) }
            composable(NavRoutes.SettingsScreen.route) { SettingsScreen() }
        }
        BottomNavigationBar(navController = navController)
    }
}