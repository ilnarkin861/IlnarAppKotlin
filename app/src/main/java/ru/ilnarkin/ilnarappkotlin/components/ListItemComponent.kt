package ru.ilnarkin.ilnarappkotlin.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.ilnarkin.ilnarappkotlin.R


@Composable
fun ListItemComponent(text: String) {

    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                color = colorResource(R.color.textColor),
                fontWeight = FontWeight.SemiBold,
                text = text
            )
            Row {
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
                        },
                        leadingIcon = {Icon(painter = painterResource(R.drawable.ic_pencil),
                            contentDescription = "Edit",
                            tint = colorResource(R.color.textColor))},
                        text = {Text(text = "Изменить", color = colorResource(R.color.textColor))}
                    )

                    DropdownMenuItem(
                        onClick = {
                            expanded = false
                        },
                        leadingIcon = {Icon(painter = painterResource(R.drawable.ic_delete),
                            contentDescription = "Delete",
                            tint = Color.Red)},
                        text = {Text(text = "Удалить", color = Color.Red)}
                    )
                }
            }

        }

    }
    HorizontalDivider(thickness = 0.5.dp, color = colorResource(R.color.borderColor))
}