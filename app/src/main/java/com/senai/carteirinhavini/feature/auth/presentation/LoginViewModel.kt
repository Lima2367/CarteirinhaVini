package com.senai.carteirinhavini.feature.auth.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senai.carteirinhavini.feature.auth.data.repository.FakeLoginRepositoryImpl
import com.senai.carteirinhavini.feature.auth.data.repository.LoginRepository
import com.senai.carteirinhavini.feature.auth.presentation.LoginEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class LoginViewModel(
    private val repository: LoginRepository = FakeLoginRepositoryImpl()
) : ViewModel() {


    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()


    fun onEvent(event: LoginEvent) {
        when (event) {


            is LoginEvent.OnUsuarioChange -> {
                _uiState.update { state ->
                    state.copy(
                        usuario = event.value,
                        errorMessage = null
                    )
                }
            }


            is LoginEvent.OnSenhaChange -> {
                _uiState.update { state ->
                    state.copy(
                        senha = event.value,
                        errorMessage = null
                    )
                }
            }


            LoginEvent.OnEntrarClick -> {
                fazerLogin()
            }


            LoginEvent.OnNavegacaoRealizado -> {
                _uiState.update { state ->
                    state.copy(
                        usuarioLogado = null
                    )
                }
            }
        }
    }


    private fun fazerLogin() {
        val state = _uiState.value


        if (state.usuario.isBlank() || state.senha.isBlank()) {
            _uiState.update {
                it.copy(
                    errorMessage = "Preencha login e senha"
                )
            }
            return
        }


        viewModelScope.launch {


            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null,
                    usuarioLogado = null
                )
            }


            val result = repository.login(
                usuario = state.usuario.trim(),
                senha = state.senha.trim()
            )


            result.fold(
                onSuccess = { usuarioLogado ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            usuarioLogado = usuarioLogado,
                            errorMessage = null
                        )
                    }
                },


                onFailure = { exception ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            usuarioLogado = null,
                            errorMessage = exception.message
                                ?: "Erro ao Fazer Login"
                        )
                    }
                }
            )
        }
    }
}



