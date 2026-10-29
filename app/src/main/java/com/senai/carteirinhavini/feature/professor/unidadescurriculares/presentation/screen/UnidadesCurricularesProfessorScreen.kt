package com.senai.carteirinhavini.feature.professor.unidadescurriculares.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.senai.carteirinhavini.feature.professor.unidadescurriculares.domain.model.UnidadeCurricularProfessor
import com.senai.carteirinhavini.feature.professor.unidadescurriculares.presentation.UnidadesCurricularesProfessorViewModel

@Composable
fun UnidadesCurricularesProfessorScreen(
    viewModel: UnidadesCurricularesProfessorViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(viewModel) { viewModel.carregar() }

    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "Consulte as unidades que você ministra, por turma e andamento das aulas.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        if (state.isLoading && state.unidades.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CircularProgressIndicator()
                Text("Carregando unidades curriculares…")
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
        if (!state.isLoading && state.errorMessage == null && state.unidades.isEmpty()) {
            Text("Nenhuma unidade curricular foi encontrada para este professor.", style = MaterialTheme.typography.bodyLarge)
        }
        if (state.unidades.isNotEmpty()) {
            Text(
                text = "${state.unidades.size} ${if (state.unidades.size == 1) "unidade encontrada" else "unidades encontradas"}",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.unidades, key = { it.id }) { unidade ->
                    UnidadeCurricularProfessorCard(unidade)
                }
            }
        }
    }
}

@Composable
private fun UnidadeCurricularProfessorCard(unidade: UnidadeCurricularProfessor) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = unidade.nome,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Turma ${unidade.turma}",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            Text("Carga horária: ${unidade.cargaHoraria} horas", style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "Aulas realizadas: ${unidade.aulasRealizadas} de ${unidade.quantidadeAulas}",
                style = MaterialTheme.typography.bodyMedium
            )
            // Barra personalizada para evitar o pequeno ponto/artefato no final
            // do indicador padrão do Material 3.
            val progresso = unidade.progressoAulas.coerceIn(0f, 1f)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                if (progresso > 0f) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(progresso)
                            .height(8.dp)
                            .clip(RoundedCornerShape(50))
                            .background(MaterialTheme.colorScheme.primary)
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = unidade.diasSemana.joinToString(separator = ", "),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "${unidade.horarioInicio} – ${unidade.horarioFim}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
