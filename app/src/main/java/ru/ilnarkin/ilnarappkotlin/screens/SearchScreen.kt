package ru.ilnarkin.ilnarappkotlin.screens


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.ilnarkin.ilnarappkotlin.R
import ru.ilnarkin.ilnarappkotlin.components.NoteItemComponent
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen (navController: NavController) {

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    var startYear = 2025
    val notSelectedYearTitle = "Год не выбран"
    var yearIsExpanded by remember { mutableStateOf(false) }
    var selectedYearTitle by remember { mutableStateOf(notSelectedYearTitle) }
    var selectedYear by remember { mutableIntStateOf(startYear) }
    var yearSelected by remember { mutableStateOf(false) }

    var years = mutableListOf<Int>()

    while (startYear <= LocalDate.now().year){
        years.add(startYear)
        startYear++
    }

    val months = arrayOf("Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль",
        "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь",)
    val notSelectedMonthTitle = "Месяц не выбран"
    var monthSelected by remember { mutableStateOf(false) }
    var monthIsExpanded by remember { mutableStateOf(false) }
    var selectedMonth by remember { mutableStateOf(notSelectedMonthTitle) }

    val noteTypes = listOf("Событие", "Заметка")
    var selectedNoteType by remember { mutableStateOf(noteTypes[0]) }
    var noteTypeMenuIsExpanded by remember { mutableStateOf(false) }

    val notSelectedArchiveTitle = "Архив не выбран"
    var archiveSelected by remember { mutableStateOf(false) }
    val archives = listOf("Архив 1", "Архив 2", "Архив 3", "Архив 4", "Архив 5")
    var archiveIsExpanded by remember { mutableStateOf(false) }
    var selectedArchive by remember { mutableStateOf(notSelectedArchiveTitle) }

    var tags = mutableListOf<String>()
    var selectedTags = remember { mutableStateListOf<String>() }

    for (i in 1..20){
        tags.add("Тег $i")
    }

    Box(modifier = Modifier.fillMaxSize()){

        Box(
            modifier = Modifier.fillMaxWidth().height(50.dp)
                .padding(horizontal = 8.dp).background(Color.White),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "В этот день год назад...",
                color = Color.Gray,
                fontSize = 18.sp
                )
        }


        LazyColumn(modifier = Modifier.padding(top = 50.dp)){
            items (10) {value -> NoteItemComponent(navController) }
        }

        if (showBottomSheet){
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,

            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 15.dp, top = 20.dp, end = 15.dp)
                    .verticalScroll(rememberScrollState()),){

                    Text(
                        modifier = Modifier.padding(bottom = 30.dp),
                        text = "Фильтр",
                        color = colorResource(R.color.titlesColor),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold)


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
                                    text = {Text(text = noteType)},
                                    colors = MenuDefaults.itemColors(textColor = colorResource(R.color.textColor)),
                                    onClick = {
                                        selectedNoteType = noteType
                                        noteTypeMenuIsExpanded = false
                                    }
                                )
                            }
                        }
                    }


                    // Year dropdown menu
                    ExposedDropdownMenuBox(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                        expanded = yearIsExpanded,
                        onExpandedChange = { yearIsExpanded = !yearIsExpanded }
                    ){
                        OutlinedTextField(
                            modifier = Modifier.menuAnchor(type = MenuAnchorType.PrimaryNotEditable)
                                .fillMaxWidth(),
                            value = selectedYearTitle,
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
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = yearIsExpanded)}
                        )
                        ExposedDropdownMenu(
                            expanded = yearIsExpanded,
                            onDismissRequest = { yearIsExpanded = false},
                            containerColor = Color.White
                        ) {
                            DropdownMenuItem(
                                modifier = Modifier.background(Color.White),
                                colors = MenuDefaults.itemColors(textColor = colorResource(R.color.textColor)),
                                text = {Text(text = notSelectedYearTitle)},
                                onClick = {
                                    selectedYearTitle = notSelectedYearTitle
                                    yearSelected = false
                                    yearIsExpanded = false
                                }
                            )

                            years.forEach { year ->
                                DropdownMenuItem(
                                    modifier = Modifier.background(Color.White),
                                    colors = MenuDefaults.itemColors(textColor = colorResource(R.color.textColor)),
                                    text = {Text(text = year.toString())},
                                    onClick = {
                                        selectedYearTitle = year.toString()
                                        selectedYear = year
                                        yearSelected = true
                                        yearIsExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    if (yearSelected){
                        // Months dropdown menu
                        ExposedDropdownMenuBox(
                            modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                            expanded = monthIsExpanded,
                            onExpandedChange = { monthIsExpanded = !monthIsExpanded }
                        ){
                            OutlinedTextField(
                                modifier = Modifier.menuAnchor(type = MenuAnchorType.PrimaryNotEditable)
                                    .fillMaxWidth(),
                                value = selectedMonth,
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
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = yearIsExpanded)}
                            )
                            ExposedDropdownMenu(
                                expanded = monthIsExpanded,
                                onDismissRequest = { monthIsExpanded = false},
                                containerColor = Color.White
                            ) {
                                DropdownMenuItem(
                                    modifier = Modifier.background(Color.White),
                                    colors = MenuDefaults.itemColors(textColor = colorResource(R.color.textColor)),
                                    text = {Text(text = notSelectedMonthTitle)},
                                    onClick = {
                                        selectedMonth = notSelectedYearTitle
                                        monthSelected = false
                                        monthIsExpanded = false
                                    }
                                )

                                months.forEach { month ->
                                    DropdownMenuItem(
                                        modifier = Modifier.background(Color.White),
                                        colors = MenuDefaults.itemColors(textColor = colorResource(R.color.textColor)),
                                        text = {Text(text = month)},
                                        onClick = {
                                            selectedMonth = month
                                            monthSelected = true
                                            monthIsExpanded = false
                                        }
                                    )
                                }
                            }
                        }
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

                    //Tags
                    Row(modifier = Modifier.padding(bottom = 5.dp)) {
                        Text(
                            text="Теги ",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray
                        )
                    }

                    Column(modifier = Modifier.padding(bottom = 55.dp)
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


                    // Search button
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                            .height(60.dp),
                        shape = RoundedCornerShape(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.appPrimaryColor)),
                        onClick = {  }
                    ) {
                        Text(text = "Искать")
                    }
                }
            }
        }

        FloatingActionButton(
            containerColor = colorResource(R.color.appPrimaryColor),
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier.align(Alignment.BottomEnd)
                .absolutePadding(bottom=30.dp, right=30.dp).background(Color.Transparent),
            onClick = {showBottomSheet = true  }) {
            Icon(painter = painterResource(R.drawable.ic_filter), contentDescription = "Добавить")
        }
    }
}