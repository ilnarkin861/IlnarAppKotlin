package ru.ilnarkin.ilnarappkotlin.components


import java.time.LocalDate
import java.time.format.DateTimeFormatter
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import ru.ilnarkin.ilnarappkotlin.R


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteDatePicker() {
    var noteDate by remember {mutableStateOf(LocalDate.now())}
    val dateDialogState = rememberMaterialDialogState()
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd.MM.yyyy")
                .format(noteDate)
        }
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().padding(top = 25.dp),
        readOnly = true,
        enabled = false,
        value = formattedDate,
        onValueChange = {},
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(R.color.borderColor),
            unfocusedBorderColor = colorResource(R.color.borderColor),
            focusedTextColor = colorResource(R.color.titlesColor),
            unfocusedTextColor = colorResource(R.color.titlesColor)
        ),
        trailingIcon = {
            IconButton(onClick = { dateDialogState.show() }) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Выбрать дату",
                    tint = colorResource(R.color.appPrimaryColor)
                )
            }
        },
        shape = RoundedCornerShape(0.dp))

    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(
                text = "Ок",
                textStyle = TextStyle(color = colorResource(R.color.appPrimaryColor)),
                onClick = { dateDialogState.hide()},
            )
            negativeButton(
                text = "Закрыть",
                textStyle = TextStyle(color = colorResource(R.color.appPrimaryColor)))
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Выбрать дату",
            colors = DatePickerDefaults.colors(
                headerBackgroundColor = colorResource(R.color.appPrimaryColor),
                dateActiveBackgroundColor = colorResource(R.color.appPrimaryColor)
            )
        ) { noteDate = it }
    }
}