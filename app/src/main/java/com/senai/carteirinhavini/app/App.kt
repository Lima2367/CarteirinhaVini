package com.senai.carteirinhavini.app

import androidx.compose.runtime.Composable
import com.senai.carteirinhavini.app.navigation.AppNavHost
import com.senai.carteirinhavini.core.designsystem.theme.CarteirinhaViniTheme

@Composable
fun App() {
    CarteirinhaViniTheme {
        AppNavHost()
    }
}
