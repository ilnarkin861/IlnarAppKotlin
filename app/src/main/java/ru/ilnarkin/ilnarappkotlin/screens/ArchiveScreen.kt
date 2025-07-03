package ru.ilnarkin.ilnarappkotlin.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.ilnarkin.ilnarappkotlin.R
import ru.ilnarkin.ilnarappkotlin.components.ListItemComponent


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArchiveScreen() {
    Box(modifier = Modifier.fillMaxWidth().fillMaxHeight()){
        LazyColumn(modifier = Modifier.padding(horizontal = 20.dp)) {
            items(20) {
                    value ->  ListItemComponent("Архив ${value + 1}")
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
            onClick = { }) {
            Icon(painter = painterResource(R.drawable.ic_archive_add), contentDescription = "Добавить")
        }
    }
}