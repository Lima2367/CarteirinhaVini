package com.senai.carteirinhavini.feature.teacher.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.senai.carteirinhavini.app.navigation.Routes
import com.senai.carteirinhavini.core.ui.SenaiBorder
import com.senai.carteirinhavini.core.ui.SenaiButton
import com.senai.carteirinhavini.core.ui.SenaiScreen
import com.senai.carteirinhavini.core.ui.SenaiText

@Composable
fun ProfessorHomeScreen(navController: NavController) {
    SenaiScreen {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.height(20.dp))
            Text("Professor", color = SenaiText, fontSize = 14.sp)
            Spacer(Modifier.height(26.dp))
            SenaiButton("Turmas", { navController.navigate(Routes.ProfessorTurmas) }, Modifier.fillMaxWidth())
            Spacer(Modifier.height(14.dp))
            SenaiButton("Unidade curricular + série", { navController.navigate(Routes.professorUnidades("2º DEV A")) }, Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun ProfessorTurmasScreen(navController: NavController) {
    val turmas = listOf("2º DEV A", "2º DEV B", "1º DEV A")
    SenaiScreen {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Turmas", color = SenaiText, fontSize = 16.sp)
            Spacer(Modifier.height(30.dp))
            turmas.forEach { turma ->
                SenaiButton(turma, { navController.navigate(Routes.professorUnidades(turma)) }, Modifier.fillMaxWidth())
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun ProfessorUnidadesScreen(navController: NavController, turma: String) {
    val unidades = listOf("PEND", "PSOF", "TSOP", "PPDM", "AJU")
    SenaiScreen {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(turma, color = SenaiText, fontSize = 16.sp)
            Spacer(Modifier.height(8.dp))
            Text("Unidade curricular", color = SenaiText, fontSize = 12.sp)
            Spacer(Modifier.height(26.dp))
            unidades.forEach { unidade ->
                SenaiButton(unidade, { navController.navigate(Routes.professorNotas(turma, unidade)) }, Modifier.fillMaxWidth())
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun ProfessorNotasScreen(turma: String, unidade: String) {
    val alunos = listOf("Aluno 01", "Aluno 02", "Aluno 03", "Aluno 04", "Aluno 05")
    val presence = remember { mutableStateMapOf<String, Boolean>() }

    SenaiScreen {
        Column(Modifier.fillMaxWidth()) {
            Text("$turma  •  $unidade", color = SenaiText, fontSize = 14.sp)
            Spacer(Modifier.height(24.dp))
            Row(Modifier.fillMaxWidth()) {
                Text("Aluno", color = SenaiText, modifier = Modifier.weight(1f), fontSize = 11.sp)
                Text("Nota", color = SenaiText, modifier = Modifier.weight(.35f), fontSize = 11.sp)
                Text("Presença", color = SenaiText, modifier = Modifier.weight(.6f), fontSize = 11.sp)
            }
            Spacer(Modifier.height(10.dp))
            alunos.forEachIndexed { index, aluno ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(aluno, color = SenaiText, modifier = Modifier.weight(1f), fontSize = 11.sp)
                    Text("${7 + index % 3},${index + 1}", color = SenaiText, modifier = Modifier.weight(.35f), fontSize = 11.sp)
                    Checkbox(
                        checked = presence[aluno] == true,
                        onCheckedChange = { presence[aluno] = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = SenaiBorder,
                            uncheckedColor = SenaiBorder,
                            checkmarkColor = SenaiText
                        )
                    )
                }
            }
        }
    }
}
