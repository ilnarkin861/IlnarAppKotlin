package ru.ilnarkin.ilnarappkotlin.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.ilnarkin.ilnarappkotlin.components.NoteFormComponent
import ru.ilnarkin.ilnarappkotlin.viewModels.ArchiveViewModel
import ru.ilnarkin.ilnarappkotlin.viewModels.NoteViewModel
import ru.ilnarkin.ilnarappkotlin.viewModels.TagViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteEditScreen(
    noteViewModel: NoteViewModel = viewModel(),
    tagViewModel: TagViewModel = viewModel(),
    archiveViewModel: ArchiveViewModel = viewModel()) {

    Column (modifier = Modifier.fillMaxSize().background(Color.White)){
        NoteFormComponent()
    }
}