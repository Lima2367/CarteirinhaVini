package com.senai.carteirinhavini.feature.carteirinha.presetantion.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.senai.carteirinhavini.R
import com.senai.carteirinhavini.feature.carteirinha.presetantion.component.PerfilAluno
import com.senai.carteirinhavini.feature.carteirinha.presetantion.component.QrCode
import com.senai.carteirinhavini.feature.login.domain.model.UsuarioLogado

@Composable
fun CarteirinhaScreen(usuarioLogado: UsuarioLogado, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "CARTEIRINHA DIGITAL",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(24.dp)),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(androidx.compose.ui.graphics.Color(0xFFE5242A)).padding(vertical = 10.dp), contentAlignment = Alignment.Center) {
                    Image(painter = painterResource(R.drawable.logo), contentDescription = "Logo SENAI", modifier = Modifier.fillMaxWidth(0.72f))
                }
                Text("IDENTIFICAÇÃO DO ALUNO", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                PerfilAluno(nome = usuarioLogado.nome, matricula = usuarioLogado.matricula, curso = usuarioLogado.curso)
                QrCode(conteudo = usuarioLogado.matricula)
                Text("SENAI • Carteirinha digital", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
