package ru.ilnarkin.ilnarappkotlin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import ru.ilnarkin.ilnarappkotlin.R


@Composable
fun ConfirmComponent(closed: () -> Unit, confirmed: () -> Unit){

    val dialogState = rememberMaterialDialogState()
    dialogState.show()

    MaterialDialog(
        dialogState = dialogState,
        onCloseRequest = {
            closed()
        },
        shape = MaterialTheme.shapes.extraSmall,
        buttons = {
            negativeButton(
                text = "Нет",
                textStyle = TextStyle(
                    color = colorResource(R.color.titlesColor),
                    fontWeight = FontWeight.SemiBold),
                onClick = {
                    dialogState.hide()
                    closed()
                }
            )

            positiveButton(
                text = "Да",
                textStyle = TextStyle(
                    color = colorResource(R.color.errorColor),
                    fontWeight = FontWeight.SemiBold),
                onClick = {
                    dialogState.hide()
                    closed()
                    confirmed()
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.background(Color.White),

            ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 15.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.size(60.dp),
                    painter = painterResource(R.drawable.ic_warning),
                    contentDescription = "",
                    tint = colorResource(R.color.warningColor))
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp, bottom = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Точно хочешь удалить?",
                    fontWeight = FontWeight.Medium,
                    color = colorResource(R.color.warningColor)
                )
            }
        }
    }
}