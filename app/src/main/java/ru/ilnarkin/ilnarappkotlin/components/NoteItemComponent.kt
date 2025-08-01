package ru.ilnarkin.ilnarappkotlin.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.ilnarkin.ilnarappkotlin.R
import ru.ilnarkin.ilnarappkotlin.routes.NavRoutes


@Composable
fun NoteItemComponent(navController: NavController) {

    var expanded by remember { mutableStateOf(false) }
    var deleteButtonClicked by remember { mutableStateOf(false) }

    if (deleteButtonClicked){
        ConfirmComponent(
            closed = {deleteButtonClicked = false},
            confirmed = {
                deleteButtonClicked = false
                //....
            }
        )
    }

    Box(modifier = Modifier.fillMaxWidth()
        .padding(10.dp)
        .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))){
        Box(modifier = Modifier.fillMaxSize()
            .background(color = Color.White)
            .defaultMinSize(minHeight = 150.dp)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(10.dp),
                color = colorResource(R.color.borderColor))){
            Column(Modifier.padding(start = 15.dp, top = 20.dp, end = 15.dp, bottom = 25.dp)) {
                Row(
                    modifier = Modifier.padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Row(modifier = Modifier.weight(3f))  {
                        Text(
                            text = "Постоянный количественный рост бодрит",
                            fontSize = 16.sp,
                            color = colorResource(R.color.titlesColor),
                            fontWeight = FontWeight.SemiBold)
                    }
                    Row{
                        IconButton(onClick = { expanded = true }, modifier = Modifier.size(24.dp)) {
                            Icon(Icons.Default.MoreVert,
                                contentDescription = "Меню",
                                tint = Color.Gray)
                        }

                        DropdownMenu(
                            modifier = Modifier.background(Color.White),
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        )
                        {

                            DropdownMenuItem(
                                onClick = {
                                    expanded = false
                                    navController.navigate(NavRoutes.NoteViewScreen.route) {
                                        launchSingleTop = false
                                        restoreState = true
                                    }
                                },
                                leadingIcon = {Icon(
                                    painter = painterResource(R.drawable.ic_view),
                                    contentDescription = "View",
                                    tint = colorResource(R.color.appPrimaryColor))},
                                text = { Text(text = "Просмотр", color = colorResource(R.color.appPrimaryColor)) }
                            )

                            DropdownMenuItem(
                                onClick = {
                                    expanded = false
                                    navController.navigate(NavRoutes.NoteEditScreen.route) {
                                        launchSingleTop = false
                                        restoreState = true
                                    }
                                },
                                leadingIcon = {Icon(painter = painterResource(R.drawable.ic_edit),
                                    contentDescription = "Edit",
                                    tint = colorResource(R.color.appPrimaryColor))},
                                text = {Text(text = "Изменить", color = colorResource(R.color.appPrimaryColor))}
                            )

                            DropdownMenuItem(
                                onClick = {
                                    deleteButtonClicked = true
                                    expanded = false
                                },
                                leadingIcon = {Icon(painter = painterResource(R.drawable.ic_delete),
                                    contentDescription = "Delete",
                                    tint = colorResource(R.color.errorColor))},
                                text = {Text(text = "Удалить", color = colorResource(R.color.errorColor))}
                            )
                        }
                    }
                }

                Row(modifier = Modifier.padding(bottom = 20.dp)) {
                    Text(
                        text = stringResource(R.string.longTestText),
                        color = colorResource(R.color.textColor),
                        maxLines = 3,
                        lineHeight = 1.5.em,
                        fontSize = 14.sp)
                }

                Row {
                    HorizontalDivider(thickness = 0.5.dp, color = colorResource(R.color.borderColor))
                }

                Row(modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier.padding(end = 4.dp).size(15.dp),
                            painter = painterResource(R.drawable.ic_calendar),
                            contentDescription = "Date",
                            tint = colorResource(R.color.appPrimaryColor)
                        )
                        Text(
                            modifier = Modifier.alpha(0.7f),
                            text = "24.06.2025",
                            color = Color.Gray
                        )
                    }
                }

                /*Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(modifier = Modifier.padding(end = 4.dp).size(15.dp),
                        painter = painterResource(R.drawable.ic_photo),
                        contentDescription = "Photos",
                        tint = colorResource(R.color.appPrimaryColor))
                    Text(modifier = Modifier.alpha(0.7f),
                        text = "0",
                        color = Color.Gray)
                }*/
            }

        }
    }
}