package ru.ilnarkin.ilnarappkotlin.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
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
import ru.ilnarkin.ilnarappkotlin.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteTypeDropdownMenu (noteTypes: List<String>){

    var isExpanded by remember { mutableStateOf(false) }
    var selectedNoteType by remember { mutableStateOf(noteTypes[0]) }

    ExposedDropdownMenuBox(
        modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
        expanded = isExpanded,
        onExpandedChange = { isExpanded = !isExpanded }
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
                focusedTextColor = colorResource(R.color.titlesColor),
                unfocusedTextColor = colorResource(R.color.titlesColor)
            ),
            shape = RoundedCornerShape(0.dp),
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)}
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false},
            containerColor = Color.White
        ) {
            noteTypes.forEach {noteType ->
                DropdownMenuItem(
                    modifier = Modifier.background(Color.White),
                    text = {Text(text = noteType)},
                    onClick = {
                        selectedNoteType = noteType
                        isExpanded = false
                    }
                )
            }
        }
    }
}