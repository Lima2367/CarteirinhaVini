package com.senai.carteirinhavini.feature.auth.presentation.Screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.senai.carteirinhavini.feature.auth.domain.model.UsuarioLogado
import com.senai.carteirinhavini.feature.auth.presentation.LoginEvent
import com.senai.carteirinhavini.feature.auth.presentation.LoginViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginSucesso: (UsuarioLogado) -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.usuarioLogado) {
        uiState.usuarioLogado?.let { usuario ->
            viewModel.onEvent(LoginEvent.OnNavegacaoRealizado)
            onLoginSucesso(usuario)
        }
    }

    LoginContent(
        uiState = uiState,
        viewModel = viewModel,
        modifier = modifier.fillMaxSize()
    )
}
