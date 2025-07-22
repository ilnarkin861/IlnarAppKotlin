package ru.ilnarkin.ilnarappkotlin.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.ilnarkin.ilnarappkotlin.R
import ru.ilnarkin.ilnarappkotlin.components.ItemModalFormComponent
import ru.ilnarkin.ilnarappkotlin.components.ListItemComponent
import ru.ilnarkin.ilnarappkotlin.viewModels.ArchiveViewModel


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArchiveScreen(archiveViewModel: ArchiveViewModel = viewModel()) {
    Box(modifier = Modifier.fillMaxSize()){

        var modalFormOpened by remember { mutableStateOf(false) }
        var itemText by remember { mutableStateOf("") }
        var itemFormLabel by remember { mutableStateOf("") }

        if (modalFormOpened){
            ItemModalFormComponent(
                itemText,
                itemFormLabel,
                closed = { modalFormOpened = false }
            )
        }

        /*Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            Text("Архивов нет",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }*/

        LazyColumn(modifier = Modifier.padding(horizontal = 20.dp)) {
            items(20) {value ->
                ListItemComponent(
                    "Архив ${value + 1}",
                    currentItem = { text ->
                        itemText = text
                        itemFormLabel = "Изменить архив"
                        modalFormOpened = true
                    })
            }
            item {
                Row(modifier = Modifier.padding(bottom = 100.dp)) {  }
            }
        }

        FloatingActionButton(
            containerColor = colorResource(R.color.appPrimaryColor),
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier.align(Alignment.BottomEnd)
                .absolutePadding(bottom=30.dp, right=30.dp).background(Color.Transparent),
            onClick = {
                itemText = ""
                itemFormLabel = "Добавить архив"
                modalFormOpened = true
            }) {
            Icon(painter = painterResource(R.drawable.ic_archive_add), contentDescription = "Добавить")
        }
    }
}