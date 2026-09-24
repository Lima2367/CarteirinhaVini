package com.senai.carteirinhavini.feature.carteirinha.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senai.carteirinhavini.R
import com.senai.carteirinhavini.feature.carteirinha.presentation.Component.QrCode

@Composable
fun CarteirinhaScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_senaisp),
            contentDescription = "Logo SENAI",
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Image(
            painter = painterResource(id = R.drawable.thukuna),
            contentDescription = "Foto do aluno",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Vinicius Lima Vieira",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Desenvolvimento de Sistemas",
                fontSize = 20.sp
            )
            Text(
                text = "Matrícula: 90000000001755912710",
                fontSize = 16.sp
            )
        }

        QrCode(
            conteudo = "90000000001755912710",
            modifier = Modifier.size(180.dp)
        )
    }
}
