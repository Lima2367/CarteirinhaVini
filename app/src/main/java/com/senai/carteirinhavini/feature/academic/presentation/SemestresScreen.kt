package com.senai.carteirinhavini.feature.academic.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.senai.carteirinhavini.app.navigation.Routes
import com.senai.carteirinhavini.core.ui.SenaiButton
import com.senai.carteirinhavini.core.ui.SenaiScreen
import com.senai.carteirinhavini.core.ui.SenaiText

@Composable
fun SemestresScreen(navController: NavController) {
    SenaiScreen {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text("Unidade curricular", color = SenaiText, fontSize = 15.sp)
            Spacer(Modifier.height(30.dp))
            (1..4).forEach { semestre ->
                SenaiButton(
                    text = when (semestre) {
                        1 -> "Primeiro semestre"
                        2 -> "Segundo semestre"
                        3 -> "Terceiro semestre"
                        else -> "Quarto semestre"
                    },
                    onClick = { navController.navigate(Routes.notasPresenca(semestre)) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}
