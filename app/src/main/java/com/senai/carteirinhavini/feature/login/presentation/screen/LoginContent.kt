package com.senai.carteirinhavini.feature.login.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senai.carteirinhavini.R
import com.senai.carteirinhavini.core.designsystem.theme.CarteirinhaDigital2DEVESTTheme
import com.senai.carteirinhavini.feature.login.presentation.LoginEvent
import com.senai.carteirinhavini.feature.login.presentation.LoginUiState

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onEvent:(LoginEvent)-> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .background(Color(0xFF191919))
            .padding(horizontal = 24.dp, vertical = 18.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_senaisp),
            contentDescription = "Logo SENAI",
            modifier = Modifier
                .fillMaxWidth()
                .height(92.dp)
        )
        Text(
            text = "Login",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        TextField(
            value = uiState.usuario,
            onValueChange = {
                onEvent(LoginEvent.OnUsuarioChange(it))
            },
            isError = uiState.credentialError,
            label = { Text(text = "Usuário") },
            shape = RoundedCornerShape(10.dp),
            colors = androidx.compose.material3.TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF292929),
                unfocusedContainerColor = Color(0xFF292929),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedLabelColor = Color(0xFFFF3333),
                unfocusedLabelColor = Color.LightGray,
                cursorColor = Color(0xFFFF3333)
            ),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = uiState.senha,
            onValueChange = {
                onEvent(LoginEvent.OnSenhaChange(it))
            },
            isError = uiState.credentialError,
            label = { Text(text = "Senha") },
            shape = RoundedCornerShape(10.dp),
            colors = androidx.compose.material3.TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF292929),
                unfocusedContainerColor = Color(0xFF292929),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedLabelColor = Color(0xFFFF3333),
                unfocusedLabelColor = Color.LightGray,
                cursorColor = Color(0xFFFF3333)
            ),
            modifier = Modifier.fillMaxWidth()
        )
        uiState.errorMessage?.let { error ->
            Text(
                text = error,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.fillMaxWidth(0.85f)
            )
        }
        Button(
            onClick = {
               onEvent(LoginEvent.OnEntrarClick)
            },
            enabled = !uiState.isLoading,
            shape = RoundedCornerShape(size = 16.dp),
            border = BorderStroke(
                width = 2.dp,
                color = Color(0xFFFF3333)
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF3333),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(.75f)
                .height(48.dp)
        ) {
            if(uiState.isLoading){
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth(0.45f)
                        .height(4.dp),
                    color = Color.White,
                    trackColor = Color.White.copy(alpha = 0.35f)
                )
            }else{
                Text(
                    "Entrar",
                    color = Color.White,
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginContentPreview() {
    CarteirinhaDigital2DEVESTTheme {
        LoginContent(
            modifier = Modifier.fillMaxSize(),
            uiState = LoginUiState(
                usuario = "aluno",
                senha = "123"
            ),
            onEvent = {}
        )
    }
}

@Preview(showBackground = true, name = "Login Error")
@Composable
private fun LoginContentErrorPreview() {
    CarteirinhaDigital2DEVESTTheme {
        LoginContent(
            modifier = Modifier.fillMaxSize(),
            uiState = LoginUiState(
                usuario = "aluno",
                senha = "000",
                errorMessage = "Login ou senha inválidos",
                credentialError = true
            ),
            onEvent = {}
        )
    }
}