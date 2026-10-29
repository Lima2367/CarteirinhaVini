package com.senai.carteirinhavini.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.senai.carteirinhavini.app.di.AppContainer
import com.senai.carteirinhavini.app.navigation.AppNavHost
import com.senai.carteirinhavini.core.designsystem.theme.CarteirinhaDigital2DEVESTTheme

@Composable
fun App(container: AppContainer) {

    var darkTheme by rememberSaveable { mutableStateOf(false) }

    CarteirinhaDigital2DEVESTTheme(
        darkTheme = darkTheme
    ) {
        val navController = rememberNavController()

        AppNavHost(
            navController = navController,
            darkTheme = darkTheme,
            onDarkThemeChange = { darkTheme = it },
            container = container
        )
    }
}