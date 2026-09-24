package com.senai.carteirinhavini.feature.academic.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.senai.carteirinhavini.core.ui.SenaiBorder
import com.senai.carteirinhavini.core.ui.SenaiScreen
import com.senai.carteirinhavini.core.ui.SenaiText

private data class AcademicRow(val sigla: String, val nota: String)

@Composable
fun NotasPresencaScreen(
    navController: NavController,
    semestre: Int
) {
    val rows = remember {
        listOf(
            AcademicRow("PEND", "8,0"),
            AcademicRow("PSOF", "7,5"),
            AcademicRow("TSOP", "9,0"),
            AcademicRow("PPDM", "8,5"),
            AcademicRow("AJU", "9,2")
        )
    }
    val checked = remember { mutableStateMapOf<String, Boolean>() }

    SenaiScreen {
        Column(Modifier.fillMaxWidth()) {
            Text(
                text = "${semestre}º semestre",
                color = SenaiText,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text("Nota", color = SenaiText, fontSize = 11.sp, modifier = Modifier.padding(end = 34.dp))
                Text("Presença", color = SenaiText, fontSize = 11.sp)
            }
            Spacer(Modifier.height(8.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                items(rows) { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(row.sigla, color = SenaiText, fontSize = 10.sp, modifier = Modifier.weight(1f))
                        Text(row.nota, color = SenaiText, fontSize = 11.sp, modifier = Modifier.padding(end = 26.dp))
                        Checkbox(
                            checked = checked[row.sigla] == true,
                            onCheckedChange = { checked[row.sigla] = it },
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
}
