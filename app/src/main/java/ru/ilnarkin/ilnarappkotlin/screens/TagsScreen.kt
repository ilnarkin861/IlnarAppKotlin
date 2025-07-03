package ru.ilnarkin.ilnarappkotlin.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.sp
import ru.ilnarkin.ilnarappkotlin.R
import ru.ilnarkin.ilnarappkotlin.components.ListItemComponent
import ru.ilnarkin.ilnarappkotlin.routes.NavRoutes


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TagsScreen () {

    Box(modifier = Modifier.fillMaxSize()){
        LazyColumn(modifier = Modifier.padding(horizontal = 20.dp)) {
            items(20) {
                value ->  ListItemComponent("Тег ${value + 1}")
            }
            item {
                Row(modifier = Modifier.padding(bottom = 100.dp)) {  }
            }
        }


        FloatingActionButton(
            containerColor = colorResource(R.color.appPrimaryColor),
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .absolutePadding(bottom = 30.dp, right = 30.dp)
                .background(Color.Transparent),
            onClick = { }) {
            Icon(painter = painterResource(R.drawable.ic_add), contentDescription = "Добавить")
        }
    }
}