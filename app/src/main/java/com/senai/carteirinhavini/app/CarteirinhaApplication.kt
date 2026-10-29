package com.senai.carteirinhavini.app

import android.app.Application
import com.senai.carteirinhavini.app.di.AppContainer
import com.senai.carteirinhavini.app.di.DefaultAppContainer

class CarteirinhaApplication : Application() {
    val container: AppContainer by lazy {
        DefaultAppContainer()
    }
}