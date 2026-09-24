package com.senai.carteirinhavini.feature.home.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.senai.carteirinhavini.app.navigation.Routes
import com.senai.carteirinhavini.feature.auth.domain.model.UsuarioLogado

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    usuarioLogado: UsuarioLogado
) {
    Column(
        modifier = modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Aluno",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = usuarioLogado.nome,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = usuarioLogado.curso,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = usuarioLogado.turma,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeNavigationButton(
                text = "Carteirinha",
                onClick = { navController.navigate(Routes.Carteirinha) }
            )
            HomeNavigationButton(
                text = "Unidades Curriculares",
                onClick = { navController.navigate(Routes.UnidadeCurricular) }
            )
        }
    }
}

@Composable
private fun HomeNavigationButton(
    text: String,
    onClick: () -> Unit
) {
    androidx.compose.material3.Button(
        modifier = Modifier.fillMaxWidth(.7f),
        onClick = onClick,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(4.dp),
        border = androidx.compose.foundation.BorderStroke(
            2.dp,
            androidx.compose.material3.MaterialTheme.colorScheme.secondary
        )
    ) {
        Text(text = text)
    }
}
