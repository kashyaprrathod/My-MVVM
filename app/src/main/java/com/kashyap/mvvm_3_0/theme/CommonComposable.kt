package com.kashyap.mvvm_3_0.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kashyap.mvvm_3_0.R

val fontFamilyPoppins = FontFamily(
    Font(R.font.poppins_extra_light, FontWeight.ExtraLight),
    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semi_bold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_extra_bold, FontWeight.ExtraBold),
    Font(R.font.poppins_black, FontWeight.Black)
)


@Composable
fun TextPoppins700(
    text: String,
    textColor: Color,
    modifier: Modifier = Modifier,
    textSize: TextUnit = 14.sp,
    maxLines: Int = Int.MAX_VALUE,
    decoration: TextDecoration = TextDecoration.None
) {
    Text(
        text = text,
        color = textColor,
        fontSize = textSize,
        modifier = modifier,
        fontFamily = fontFamilyPoppins,
        fontWeight = FontWeight.W700,
        textDecoration = decoration,
        maxLines = maxLines
    )
}

@Composable
fun TextPoppins600(
    text: String,
    textColor: Color,
    modifier: Modifier = Modifier,
    textSize: TextUnit = 14.sp
) {
    Text(
        text = text,
        color = textColor,
        fontSize = textSize,
        modifier = modifier,
        fontFamily = fontFamilyPoppins,
        fontWeight = FontWeight.W600
    )
}

@Composable
fun TextPoppins500(
    text: String,
    textColor: Color,
    modifier: Modifier = Modifier,
    textSize: TextUnit = 14.sp,
    maxLines: Int = Int.MAX_VALUE,
    decoration: TextDecoration = TextDecoration.None,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        text = text,
        color = textColor,
        fontSize = textSize,
        modifier = modifier,
        fontFamily = fontFamilyPoppins,
        fontWeight = FontWeight.W500,
        textDecoration = decoration,
        maxLines = maxLines,
        textAlign = textAlign
    )
}

@Composable
fun TextPoppins400(
    text: String,
    textColor: Color,
    modifier: Modifier = Modifier,
    textSize: TextUnit = 14.sp
) {
    Text(
        text = text,
        color = textColor,
        fontSize = textSize,
        modifier = modifier,
        fontFamily = fontFamilyPoppins,
        fontWeight = FontWeight.W400
    )
}

@Composable
fun TextPoppins300(
    text: String,
    textColor: Color,
    modifier: Modifier = Modifier,
    textSize: TextUnit = 14.sp
) {
    Text(
        text = text,
        color = textColor,
        fontSize = textSize,
        modifier = modifier,
        fontFamily = fontFamilyPoppins,
        fontWeight = FontWeight.W300
    )
}

@Composable
fun TextField500(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    textColor: Color = MaterialTheme.colorScheme.primary,
    hintColor: Color = MaterialTheme.colorScheme.secondary,
    textSize: TextUnit = 14.sp,
    onValueChanged: (String) -> Unit,
) {
    val fieldValue = remember { mutableStateOf(text) }
    TextField(
        value = fieldValue.value,
        textStyle = TextStyle(
            fontFamily = fontFamilyPoppins,
            fontWeight = FontWeight.W300
        ),
        onValueChange = {
            fieldValue.value = it
            onValueChanged(fieldValue.value)
        },
        placeholder = {
            TextPoppins500(hint, gray)
        },
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = gray2_20,
            focusedContainerColor = gray2_20,
            focusedTextColor = black,
            unfocusedIndicatorColor = transparent
        )
    )
}

