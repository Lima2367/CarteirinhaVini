package com.senai.carteirinhavini.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senai.carteirinhavini.R

val SenaiRed = Color(0xFFFF1717)
val SenaiBackground = Color(0xFF191919)
val SenaiSurface = Color(0xFF252525)
val SenaiBorder = Color(0xFF696969)
val SenaiText = Color(0xFFF4F4F4)
val SenaiMuted = Color(0xFFBDBDBD)

@Composable
fun SenaiScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SenaiBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo_senaisp),
            contentDescription = "SENAI",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 22.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            content()
        }
    }
}

@Composable
fun SenaiButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = SenaiSurface,
            contentColor = SenaiText,
            disabledContainerColor = SenaiSurface,
            disabledContentColor = SenaiMuted
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, SenaiBorder),
        modifier = modifier
            .widthIn(min = 150.dp)
            .height(42.dp)
    ) {
        Text(text = text, fontSize = 12.sp)
    }
}

@Composable
fun SenaiTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = SenaiText,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun SenaiOutlinedField(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(42.dp)
            .background(SenaiSurface, RoundedCornerShape(7.dp))
            .border(1.dp, Color(0xFF3E3E3E), RoundedCornerShape(7.dp))
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = if (value.isBlank()) label else value,
            color = if (value.isBlank()) SenaiMuted else SenaiText,
            fontSize = 11.sp
        )
    }
}

@Composable
fun SenaiInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = SenaiMuted, fontSize = 12.sp)
        Text(value, color = SenaiText, fontSize = 12.sp, fontWeight = FontWeight.Medium)
    }
}
