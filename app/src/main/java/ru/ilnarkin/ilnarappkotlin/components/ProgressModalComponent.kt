package ru.ilnarkin.ilnarappkotlin.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import ru.ilnarkin.ilnarappkotlin.R


@Composable
fun ProgressModalComponent() {

    val dialogState = rememberMaterialDialogState()
    dialogState.show()

    MaterialDialog(
        dialogState = dialogState,
        shape = MaterialTheme.shapes.extraSmall,
        onCloseRequest = { MaterialDialogState.Saver() }
    ) {
        Box(
            modifier = Modifier.background(Color.White),
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 25.dp),
                    strokeWidth = 5.dp,
                    color = colorResource(R.color.appPrimaryColor),
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
                Text(
                    text = "Подожди...",
                    color = colorResource(R.color.appPrimaryColor),
                    fontSize = 16.sp)
            }
        }
    }
}