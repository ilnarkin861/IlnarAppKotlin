package ru.ilnarkin.ilnarappkotlin.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.ilnarkin.ilnarappkotlin.R
import ru.ilnarkin.ilnarappkotlin.components.NoteItemComponent
import ru.ilnarkin.ilnarappkotlin.routes.NavRoutes


@Composable
fun NotesScreen (navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn{
            items (10) {value -> NoteItemComponent() }
        }

        FloatingActionButton(
            containerColor = colorResource(R.color.appPrimaryColor),
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier.align(Alignment.BottomEnd)
                .absolutePadding(bottom=20.dp, right=20.dp).background(Color.Transparent),
            onClick = { navController.navigate(NavRoutes.NoteAddScreen.route) }) {
            Icon(painter = painterResource(R.drawable.ic_add_notes), contentDescription = "Добавить")
        }
    }
}