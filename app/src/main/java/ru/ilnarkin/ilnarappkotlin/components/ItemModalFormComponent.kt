package ru.ilnarkin.ilnarappkotlin.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import ru.ilnarkin.ilnarappkotlin.R


@Composable
fun ItemModalFormComponent(
    itemText: String = "",
    label: String = "",
    closed: () -> Unit
    ) {

    var mutableItemText = remember { mutableStateOf(itemText) }

    var itemTextIsError by remember { mutableStateOf(false) }

    val dialogState = rememberMaterialDialogState()
    dialogState.show()

    MaterialDialog(
        dialogState = dialogState,
        onCloseRequest = { closed() },
        shape = MaterialTheme.shapes.extraSmall,
    ) {
        Column(modifier = Modifier.background(Color.White)) {

            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 30.dp, start = 15.dp, end = 15.dp, bottom = 40.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 5.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = mutableItemText.value,
                        singleLine = true,
                        label = { Text(label) },
                        isError = itemTextIsError,
                        onValueChange = { text ->
                            mutableItemText.value = text
                            itemTextIsError = mutableItemText.value.isEmpty()
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = colorResource(R.color.borderColor),
                            focusedBorderColor = colorResource(R.color.appPrimaryColor),
                            unfocusedLabelColor = Color.LightGray,
                            focusedLabelColor = colorResource(R.color.appPrimaryColor),
                            focusedTextColor = colorResource(R.color.titlesColor),
                            unfocusedTextColor = colorResource(R.color.titlesColor),
                            errorLabelColor = colorResource(R.color.errorColor),
                            errorBorderColor = colorResource(R.color.errorColor)
                        ),
                        shape = RoundedCornerShape(10.dp))
                }

                if (itemTextIsError){
                    Row{
                        Text(
                            text = "Обязательное поле",
                            color = colorResource(R.color.errorColor),
                            fontSize = 13.sp
                        )
                    }
                }

                Row(
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.appPrimaryColor)),
                        onClick = {
                            itemTextIsError = mutableItemText.value.isEmpty()

                            if(!itemTextIsError){
                                closed()
                            }
                        }
                    ) {
                        Text(text = "Сохранить")
                    }
                }
            }
        }
    }
}