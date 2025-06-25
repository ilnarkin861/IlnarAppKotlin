package ru.ilnarkin.ilnarappkotlin.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.ilnarkin.ilnarappkotlin.R


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NoteViewScreen() {

    val noteType = "Событие"
    val archiveTitle = "Название архива"
    val tags = arrayOf("Какой-то тег", "Какой-то тег2", "Какой-то тег3")
    val images = arrayOf(
        "https://avatars.mds.yandex.net/get-mpic/11047949/2a0000018c1dbabb372a82ea1a9f4eec81dc/orig",
        "https://i.pinimg.com/originals/aa/c1/1a/aac11ab900fe395deb94345d5ea2c117.jpg",
        "https://mir-s3-cdn-cf.behance.net/project_modules/1400/2f639160984749.5a5f92c5ee7b8.jpg",
        "https://avatars.mds.yandex.net/i?id=bfe6b125b2c531537640a2115be53424_l-5545493-images-thumbs&n=13"
    )


    Box(modifier = Modifier.fillMaxSize().background(Color.White)){
        Column(modifier = Modifier
            .padding(start = 15.dp, top = 20.dp, end = 15.dp, bottom = 25.dp)
            .fillMaxWidth().verticalScroll(rememberScrollState())) {
            Row {
                Text("Постоянный количественный рост бодрит",
                    fontSize = 20.sp,
                    color = colorResource(R.color.titlesColor),
                    fontWeight = FontWeight.SemiBold
                )
            }

            Row(modifier = Modifier.padding(top = 5.dp, bottom = 15.dp)) {
                Text(modifier = Modifier.alpha(0.7f),
                    text = "24.06.2025",
                    fontSize = 15.sp,
                    color = Color.Gray)
            }

            Row(
                modifier = Modifier.padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(modifier = Modifier.padding(end = 4.dp).size(20.dp),
                    painter = painterResource(R.drawable.ic_calendar),
                    contentDescription = "Note type",
                    tint = colorResource(R.color.appPrimaryColor))

                Text(
                    text = noteType,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    color = colorResource(R.color.textColor)
                )
            }

            Row(
                modifier = Modifier.padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(modifier = Modifier.padding(end = 4.dp).size(20.dp),
                    painter = painterResource(R.drawable.ic_archive),
                    contentDescription = "Note type",
                    tint = colorResource(R.color.appPrimaryColor))

                Text(
                    text = archiveTitle,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    color = colorResource(R.color.textColor)
                )
            }

            Row(
                modifier = Modifier.padding(bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(modifier = Modifier.padding(end = 4.dp).size(20.dp),
                    painter = painterResource(R.drawable.ic_tag),
                    contentDescription = "Tags",
                    tint = colorResource(R.color.appPrimaryColor))

                Text(
                    text = tags.joinToString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    color = colorResource(R.color.textColor)
                )
            }

            Row(modifier = Modifier.padding(top = 20.dp, bottom = 15.dp)) {
                HorizontalDivider(thickness = 0.5.dp, color = colorResource(R.color.borderColor))
            }

            Row {
                Text(
                    text = stringResource(R.string.longTestText),
                    color = colorResource(R.color.textColor),
                    lineHeight = 1.5.em,
                    fontSize = 16.sp)
            }

            Column(modifier = Modifier.padding(top = 70.dp)) {
                for (image in  images){
                    AsyncImage(
                        modifier = Modifier.padding(bottom = 20.dp),
                        model = image,
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}