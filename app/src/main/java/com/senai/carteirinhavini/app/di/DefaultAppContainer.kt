package com.senai.carteirinhavini.app.di

import com.senai.carteirinhavini.core.auth.SessionTokenStore
import com.senai.carteirinhavini.core.network.NetworkClient
import com.senai.carteirinhavini.feature.unidadecurriculares.data.remote.service.UnidadeCurricularApi
import com.senai.carteirinhavini.feature.unidadecurriculares.data.repository.ApiUnidadeCurricularRepositoryImpl
import com.senai.carteirinhavini.feature.unidadecurriculares.domain.repository.UnidadeCurricularRepository
import com.senai.carteirinhavini.feature.login.data.remote.service.AuthApi
import com.senai.carteirinhavini.feature.login.data.repository.ApiLoginRepositoryImpl
import com.senai.carteirinhavini.feature.login.data.repository.FakeAuthRepository
import com.senai.carteirinhavini.feature.login.domain.repository.LoginRepository
import com.senai.carteirinhavini.feature.professor.turmas.data.remote.service.TurmaProfessorApi
import com.senai.carteirinhavini.feature.professor.turmas.data.repository.ApiTurmaProfessorRepositoryImpl
import com.senai.carteirinhavini.feature.professor.turmas.domain.repository.TurmaProfessorRepository
import com.senai.carteirinhavini.feature.professor.unidadescurriculares.data.remote.service.UnidadeCurricularProfessorApi
import com.senai.carteirinhavini.feature.professor.unidadescurriculares.data.repository.ApiUnidadeCurricularProfessorRepositoryImpl
import com.senai.carteirinhavini.feature.professor.unidadescurriculares.domain.repository.UnidadeCurricularProfessorRepository

class DefaultAppContainer : AppContainer {
    override val sessionTokenStore: SessionTokenStore = SessionTokenStore()
    private val networkClient = NetworkClient(
        baseUrl = BASE_URL,
        sessionTokenStore = sessionTokenStore
    )

    private val authApi: AuthApi by lazy {
        networkClient.createPublic(AuthApi::class.java)
    }

    private val unidadeCurricularApi: UnidadeCurricularApi by lazy {
        networkClient.createAuthenticated(UnidadeCurricularApi::class.java)
    }

    private val turmaProfessorApi: TurmaProfessorApi by lazy { networkClient.createAuthenticated(TurmaProfessorApi::class.java) }
    private val unidadeCurricularProfessorApi: UnidadeCurricularProfessorApi by lazy { networkClient.createAuthenticated(UnidadeCurricularProfessorApi::class.java) }

    override val turmaProfessorRepository: TurmaProfessorRepository by lazy { ApiTurmaProfessorRepositoryImpl(api = turmaProfessorApi) }
    override val unidadeCurricularProfessorRepository: UnidadeCurricularProfessorRepository by lazy { ApiUnidadeCurricularProfessorRepositoryImpl(api = unidadeCurricularProfessorApi) }

    override val loginRepository: LoginRepository by lazy {
        if (USE_FAKE_LOGIN_REPOSITORY) {
            FakeAuthRepository()
        } else {
            ApiLoginRepositoryImpl(api = authApi)
        }
    }
    override val unidadeCurricularRepository: UnidadeCurricularRepository by lazy {
        ApiUnidadeCurricularRepositoryImpl(api = unidadeCurricularApi)
    }

    companion object {
        private const val BASE_URL = "http://10.0.2.2:8080/"
        private const val USE_FAKE_LOGIN_REPOSITORY = false
    }
}