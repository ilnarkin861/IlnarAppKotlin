package ru.ilnarkin.ilnarappkotlin.components


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.ilnarkin.ilnarappkotlin.R


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NoteFormComponent() {

    var noteTitle = remember { mutableStateOf("") }
    var noteText = remember { mutableStateOf("") }
    var isNoteTextError by remember { mutableStateOf(false) }
    val noteTypes = listOf("Событие", "Заметка")
    val archives = listOf("Архив 1", "Архив 1", "Архив 3", "Архив 4", "Архив 5")
    var tags = mutableListOf<String>()

    for (i in 1..20){
        tags.add("Тег $i")
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 15.dp, top = 20.dp, end = 15.dp, bottom = 40.dp)
        .verticalScroll(rememberScrollState()),) {

        NoteTypeDropdownMenu(noteTypes)

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            value = noteTitle.value,
            singleLine = true,
            label = { Text("Заголовок") },
            onValueChange = {text -> noteTitle.value = text},
            colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = colorResource(R.color.borderColor),
                    focusedBorderColor = colorResource(R.color.appPrimaryColor),
                    unfocusedLabelColor = Color.LightGray,
                    focusedLabelColor = colorResource(R.color.appPrimaryColor),
                    focusedTextColor = colorResource(R.color.titlesColor),
                    unfocusedTextColor = colorResource(R.color.titlesColor)
                ),
            shape = RoundedCornerShape(0.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(bottom = 10.dp),
            value = noteText.value,
            minLines = 10,
            label = { Text("Текст") },
            isError = isNoteTextError,
            onValueChange = {text ->
                noteText.value = text
                isNoteTextError = noteText.value.isEmpty()
            },
            colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = colorResource(R.color.borderColor),
                    focusedBorderColor = colorResource(R.color.appPrimaryColor),
                    unfocusedLabelColor = Color.LightGray,
                    focusedLabelColor = colorResource(R.color.appPrimaryColor),
                    focusedTextColor = colorResource(R.color.titlesColor),
                    unfocusedTextColor = colorResource(R.color.titlesColor),
                    errorLabelColor = Color.Red,
                    errorBorderColor = Color.Red
                ),
            shape = RoundedCornerShape(0.dp))

        if (isNoteTextError){
            Text(
                text = "Обязательное поле",
                color = Color.Red,
                fontSize = 13.sp,
                fontWeight = FontWeight.Light
            )
        }

        NoteDatePicker()

        ArchiveDropdownMenu(archives)

        TagsSelect(tags)

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
                .height(60.dp),
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.appPrimaryColor)),
            onClick = {
                isNoteTextError = noteText.value.isEmpty()
            }
        ) {
            Text(text = "Сохранить")
        }
    }
}