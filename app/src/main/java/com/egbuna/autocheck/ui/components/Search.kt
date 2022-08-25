package com.egbuna.autocheck.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit) {
    TextField(
        value = text,
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search,
                tint = Color.DarkGray,
                contentDescription = null)
        },
        textStyle = TextStyle(fontSize = 12.sp),
        onValueChange = {
            onTextChanged.invoke(it)
        },
        placeholder = {
            Text(
                text = "Search",
                fontSize = 12.sp,
                color = Color.Black
            )
        },
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = Color.Black,
            textColor = Color.Black,
            backgroundColor = Color(0xFFFAFAFA)
        ),
        modifier = modifier.height(47.dp)
            .border(3.dp, shape = RoundedCornerShape(10.dp), color = Color.White)
    )
}

@Preview(showBackground = true, name = "search", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
@Composable
fun SearchPreview() {
    SearchTextField(text = "Search", onTextChanged = {})
}
