package com.senai.carteirinhavini.feature.professor.turmas.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.senai.carteirinhavini.feature.professor.turmas.domain.model.TurmaProfessor
import com.senai.carteirinhavini.feature.professor.turmas.presentation.TurmasProfessorViewModel

@Composable
fun TurmasProfessorScreen(
    viewModel: TurmasProfessorViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(viewModel) { viewModel.carregar() }

    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "Acompanhe as turmas vinculadas ao seu perfil.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        if (state.isLoading && state.turmas.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CircularProgressIndicator()
                Text("Carregando suas turmas…")
            }
        }
        if (state.errorMessage != null) {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(state.errorMessage!!, color = MaterialTheme.colorScheme.onSurface)
                    Button(onClick = viewModel::carregar, enabled = !state.isLoading) {
                        Text("Tentar novamente")
                    }
                }
            }
        }
        if (!state.isLoading && state.errorMessage == null && state.turmas.isEmpty()) {
            Text("Nenhuma turma foi encontrada para este professor.", style = MaterialTheme.typography.bodyLarge)
        }
        if (state.turmas.isNotEmpty()) {
            Text(
                text = "${state.turmas.size} ${if (state.turmas.size == 1) "turma encontrada" else "turmas encontradas"}",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.turmas, key = { it.id }) { turma ->
                    TurmaProfessorCard(turma)
                }
            }
        }
    }
}

@Composable
private fun TurmaProfessorCard(turma: TurmaProfessor) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = turma.codigo,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(turma.curso, style = MaterialTheme.typography.titleMedium)
            Text("Semestre: ${turma.semestre}", style = MaterialTheme.typography.bodyMedium)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Alunos: ${turma.quantidadeAlunos}", style = MaterialTheme.typography.bodyMedium)
                Text("${turma.horarioInicio} – ${turma.horarioFim}", style = MaterialTheme.typography.bodyMedium)
            }
            Text(
                text = "Dias: ${turma.diasSemana.joinToString(separator = ", ")}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
