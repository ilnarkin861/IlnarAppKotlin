package ru.ilnarkin.ilnarappkotlin.components


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import ru.ilnarkin.ilnarappkotlin.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NoteFormComponent() {

    var addedTags = mutableListOf<String>()

    for (i in 1..5){
        addedTags.add("Добавленный тег $i")
    }


    var noteTitle = remember { mutableStateOf("") }

    var noteText = remember { mutableStateOf("") }
    var isNoteTextError by remember { mutableStateOf(false) }

    var noteDate by remember {mutableStateOf(LocalDate.now())}
    val dateDialogState = rememberMaterialDialogState()
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd.MM.yyyy")
                .format(noteDate)
        }
    }

    val noteTypes = listOf("Событие", "Заметка")
    var selectedNoteType by remember { mutableStateOf(noteTypes[0]) }
    var noteTypeMenuIsExpanded by remember { mutableStateOf(false) }

    val notSelectedArchiveTitle = "Архив не выбран"
    var archiveSelected by remember { mutableStateOf(false) }
    val archives = listOf("Архив 1", "Архив 2", "Архив 3", "Архив 4", "Архив 5")
    var archiveIsExpanded by remember { mutableStateOf(false) }
    var selectedArchive by remember { mutableStateOf(notSelectedArchiveTitle) }

    var tags = mutableListOf<String>()
    var alreadyTags = remember { addedTags.toMutableStateList() }
    var selectedTags = remember { mutableStateListOf<String>() }
    var selectedTagsCount = remember { mutableIntStateOf(0) }
    var uploadableTags = mutableListOf<String>()


    for (i in 1..20){
        tags.add("Тег $i")
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 15.dp, top = 20.dp, end = 15.dp)
        .verticalScroll(rememberScrollState()),) {


        //Note type dropdown menu
        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
            expanded = noteTypeMenuIsExpanded,
            onExpandedChange = { noteTypeMenuIsExpanded = !noteTypeMenuIsExpanded }
        ){
            OutlinedTextField(
                modifier = Modifier.menuAnchor(type = MenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth(),
                value = selectedNoteType,
                onValueChange = {},
                readOnly = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = colorResource(R.color.borderColor),
                    focusedBorderColor = colorResource(R.color.appPrimaryColor),
                    unfocusedLabelColor = Color.LightGray,
                    focusedLabelColor = colorResource(R.color.appPrimaryColor),
                    focusedTrailingIconColor = colorResource(R.color.appPrimaryColor),
                    unfocusedTrailingIconColor = colorResource(R.color.appPrimaryColor),
                    focusedTextColor = colorResource(R.color.textColor),
                    unfocusedTextColor = colorResource(R.color.textColor)
                ),
                shape = RoundedCornerShape(0.dp),
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = noteTypeMenuIsExpanded)}
            )
            ExposedDropdownMenu(
                expanded = noteTypeMenuIsExpanded,
                onDismissRequest = { noteTypeMenuIsExpanded = false},
                containerColor = Color.White
            ) {
                noteTypes.forEach {noteType ->
                    DropdownMenuItem(
                        modifier = Modifier.background(Color.White),
                        colors = MenuDefaults.itemColors(textColor = colorResource(R.color.textColor)),
                        text = {Text(text = noteType)},
                        onClick = {
                            selectedNoteType = noteType
                            noteTypeMenuIsExpanded = false
                        }
                    )
                }
            }
        }


        //Note title field
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


        //Note text field
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
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
                fontSize = 13.sp
            )
        }


        // Note date picker field
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(top = 28.dp, bottom = 28.dp),
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
                    textStyle = TextStyle(
                        color = colorResource(R.color.appPrimaryColor),
                        fontWeight = FontWeight.SemiBold),
                    onClick = { dateDialogState.hide()},
                )
                negativeButton(
                    text = "Закрыть",
                    textStyle = TextStyle(
                        color = colorResource(R.color.appPrimaryColor),
                        fontWeight = FontWeight.SemiBold)
                )
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


        // Archive dropdown menu
        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
            expanded = archiveIsExpanded,
            onExpandedChange = { archiveIsExpanded = !archiveIsExpanded }
        ){
            OutlinedTextField(
                modifier = Modifier.menuAnchor(type = MenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth(),
                value = selectedArchive,
                onValueChange = {},
                readOnly = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = colorResource(R.color.borderColor),
                    focusedBorderColor = colorResource(R.color.appPrimaryColor),
                    unfocusedLabelColor = Color.LightGray,
                    focusedLabelColor = colorResource(R.color.appPrimaryColor),
                    focusedTrailingIconColor = colorResource(R.color.appPrimaryColor),
                    unfocusedTrailingIconColor = colorResource(R.color.appPrimaryColor),
                    focusedTextColor = colorResource(R.color.textColor),
                    unfocusedTextColor = colorResource(R.color.textColor)
                ),
                shape = RoundedCornerShape(0.dp),
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = archiveIsExpanded)}
            )
            ExposedDropdownMenu(
                expanded = archiveIsExpanded,
                onDismissRequest = { archiveIsExpanded = false},
                containerColor = Color.White
            ) {
                DropdownMenuItem(
                    modifier = Modifier.background(Color.White),
                    colors = MenuDefaults.itemColors(textColor = colorResource(R.color.textColor)),
                    text = {Text(text = notSelectedArchiveTitle)},
                    onClick = {
                        selectedArchive = notSelectedArchiveTitle
                        archiveSelected = false
                        archiveIsExpanded = false
                    }
                )
                archives.forEach {archive ->
                    DropdownMenuItem(
                        modifier = Modifier.background(Color.White),
                        colors = MenuDefaults.itemColors(textColor = colorResource(R.color.textColor)),
                        text = {Text(text = archive)},
                        onClick = {
                            selectedArchive = archive
                            archiveSelected = true
                            archiveIsExpanded = false
                        }
                    )
                }
            }
        }


        //Tags editor
        Row(modifier = Modifier.padding(top = 30.dp, bottom = 5.dp)) {
            Text(
                text="Выбрать теги ",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray
            )

            Text(
                text="(выбрано: ${selectedTagsCount.intValue})",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Column(modifier = Modifier
            .border(1.dp, color = colorResource(R.color.borderColor))) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(top = 10.dp)
                    .verticalScroll(rememberScrollState())) {
                tags.forEachIndexed { index, tag ->
                    var checkedTag by remember { mutableStateOf(false) }

                    Row(
                        modifier = Modifier.padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){

                        Checkbox(
                            modifier = Modifier.height(20.dp),
                            checked = checkedTag,
                            onCheckedChange = {
                                val existedTag = selectedTags.contains(tag)

                                if(!existedTag) {
                                    selectedTags.add(tag)
                                }

                                else {
                                    selectedTags.remove(tag)
                                }

                                selectedTagsCount.intValue = selectedTags.count()
                                checkedTag = !checkedTag
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = colorResource(R.color.appPrimaryColor),
                                uncheckedColor = colorResource(R.color.borderColor))
                        )

                        Text(
                            text = tag,
                            color = colorResource(R.color.titlesColor)
                        )
                    }
                }
            }
        }

        if (alreadyTags.count() != 0){
            Row(modifier = Modifier.padding(top = 30.dp, bottom = 5.dp)) {
                Text(
                    text="Добавленные теги",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
            }
            Column(modifier = Modifier
                .border(1.dp, color = colorResource(R.color.borderColor))){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)){
                    alreadyTags.forEachIndexed{index, tag ->
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 5.dp)
                        ) {
                            Text(text = tag)
                            IconButton(
                                modifier = Modifier.size(20.dp),
                                onClick = {
                                    alreadyTags.removeAt(index)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Delete,
                                    contentDescription = "",
                                    tint = Color.Red)
                            }
                        }
                    }
                }
            }
        }


        // Save button
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, bottom = 40.dp)
                .height(60.dp),
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.appPrimaryColor)),
            onClick = {
                isNoteTextError = noteText.value.isEmpty()

                if(!isNoteTextError){
                    uploadableTags.clear()
                    uploadableTags.addAll(selectedTags)
                    uploadableTags.addAll(alreadyTags)


                }
            }
        ) {
            Text(text = "Сохранить")
        }
    }
}