package ru.ilnarkin.ilnarappkotlin.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.ilnarkin.ilnarappkotlin.components.NoteFormComponent


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteAddScreen () {
    Column (modifier = Modifier.fillMaxSize().background(Color.White)){
        NoteFormComponent()
    }
}