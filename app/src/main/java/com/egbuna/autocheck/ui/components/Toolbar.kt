package com.egbuna.autocheck.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.egbuna.autocheck.ui.theme.AutoCheckTheme

@Composable
fun Toolbar(
    onCartClicked: () -> Unit,
    @DrawableRes icon: Int,
    count: Int = 0,
    title: String,
    onBackClicked: () -> Unit = {}
) {
    ToolbarView(
        onCartClicked,
        icon,
        count,
        title,
        cartIcon = com.egbuna.autocheck.R.drawable.ic_baseline_shopping_cart_24,
        onBackClicked
    )
}

@Composable
fun ToolbarView(onCartClicked: () -> Unit,
                @DrawableRes icon: Int,
                count: Int = 0,
                title: String,
                cartIcon: Int,
                onBackClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(painter = painterResource(id = icon), contentDescription = null,
        modifier = Modifier.clickable {
            onBackClicked.invoke()
        })
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = title, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
        Spacer(modifier = Modifier.weight(1f))
        BadgedBox(
            modifier = Modifier.clickable {
                onCartClicked.invoke()
            },
            badge = {
                Badge {
                    Text(text = "$count")
                }
            }) {
            Icon(painter = painterResource(id = cartIcon),
                contentDescription = null)
        }
    }
}

@Preview(showBackground = true, name = "phone", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
@Composable
fun ToolBarPreview() {
    AutoCheckTheme {
        ToolbarView(onCartClicked = { },
            icon = com.egbuna.autocheck.R.drawable.ic_baseline_widgets_24,
            title = "Explore",
            cartIcon = com.egbuna.autocheck.R.drawable.ic_baseline_shopping_cart_24,
        onBackClicked = {})
    }
}