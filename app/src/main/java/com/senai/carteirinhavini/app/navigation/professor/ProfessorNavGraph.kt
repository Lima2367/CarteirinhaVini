package com.senai.carteirinhavini.app.navigation.professor

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.senai.carteirinhavini.app.navigation.GraphRoutes
import com.senai.carteirinhavini.app.navigation.ProfileGuard
import com.senai.carteirinhavini.app.ui.AppShell
import com.senai.carteirinhavini.feature.login.domain.model.PerfilAcesso
import com.senai.carteirinhavini.feature.login.domain.model.UsuarioLogado
import com.senai.carteirinhavini.feature.professor.home.presentation.screen.HomeProfessorScreen
import com.senai.carteirinhavini.feature.professor.turmas.domain.repository.TurmaProfessorRepository
import com.senai.carteirinhavini.feature.professor.turmas.presentation.TurmasProfessorViewModel
import com.senai.carteirinhavini.feature.professor.turmas.presentation.factory.TurmasProfessorViewModelFactory
import com.senai.carteirinhavini.feature.professor.turmas.presentation.screen.TurmasProfessorScreen
import com.senai.carteirinhavini.feature.professor.unidadescurriculares.domain.repository.UnidadeCurricularProfessorRepository
import com.senai.carteirinhavini.feature.professor.unidadescurriculares.presentation.UnidadesCurricularesProfessorViewModel
import com.senai.carteirinhavini.feature.professor.unidadescurriculares.presentation.factory.UnidadesCurricularesProfessorViewModelFactory
import com.senai.carteirinhavini.feature.professor.unidadescurriculares.presentation.screen.UnidadesCurricularesProfessorScreen
import kotlinx.coroutines.flow.StateFlow

fun NavGraphBuilder.professorNavGraph(
    navController: NavHostController,
    usuarioLogadoFlow: StateFlow<UsuarioLogado?>,
    turmaProfessorRepository: TurmaProfessorRepository,
    unidadeCurricularProfessorRepository: UnidadeCurricularProfessorRepository,
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
    onLogout: () -> Unit,
    onSessaoAusente: () -> Unit,
    onPerfilInvalido: (PerfilAcesso) -> Unit
) {
    navigation(
        startDestination = ProfessorRoutes.HOME,
        route = GraphRoutes.PROFESSOR
    ) {
        composable(route = ProfessorRoutes.HOME) {
            ProfileGuard(
                usuarioFlow = usuarioLogadoFlow,
                perfilEsperado = PerfilAcesso.PROFESSOR,
                onSessaoAusente = onSessaoAusente,
                onPerfilInvalido = onPerfilInvalido
            ) { usuario ->
                AppShell(
                    title = "Início",
                    usuarioLogado = usuario,
                    canNavigateBack = false,
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                    onNavigateBack = {},
                    onLogout = onLogout
                ) { innerPadding ->
                    HomeProfessorScreen(
                        usuarioLogado = usuario,
                        onTurmasClick = {
                            navController.navigate(ProfessorRoutes.TURMAS)
                        },
                        onUnidadesCurricularesClick = {
                            navController.navigate(ProfessorRoutes.UNIDADES_CURRICULARES)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }

        composable(route = ProfessorRoutes.TURMAS) {
            ProfileGuard(
                usuarioFlow = usuarioLogadoFlow,
                perfilEsperado = PerfilAcesso.PROFESSOR,
                onSessaoAusente = onSessaoAusente,
                onPerfilInvalido = onPerfilInvalido
            ) { usuario ->
                val factory = remember(turmaProfessorRepository) {
                    TurmasProfessorViewModelFactory(turmaProfessorRepository)
                }
                val screenViewModel: TurmasProfessorViewModel = viewModel(factory = factory)
                AppShell(
                    title = "Minhas Turmas",
                    usuarioLogado = usuario,
                    canNavigateBack = true,
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                    onNavigateBack = { navController.popBackStack() },
                    onLogout = onLogout
                ) { innerPadding ->
                    TurmasProfessorScreen(
                        viewModel = screenViewModel,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }

        composable(route = ProfessorRoutes.UNIDADES_CURRICULARES) {
            ProfileGuard(
                usuarioFlow = usuarioLogadoFlow,
                perfilEsperado = PerfilAcesso.PROFESSOR,
                onSessaoAusente = onSessaoAusente,
                onPerfilInvalido = onPerfilInvalido
            ) { usuario ->
                val factory = remember(unidadeCurricularProfessorRepository) {
                    UnidadesCurricularesProfessorViewModelFactory(
                        unidadeCurricularProfessorRepository
                    )
                }
                val screenViewModel: UnidadesCurricularesProfessorViewModel = viewModel(factory = factory)
                AppShell(
                    title = "Unidades Curriculares",
                    usuarioLogado = usuario,
                    canNavigateBack = true,
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                    onNavigateBack = { navController.popBackStack() },
                    onLogout = onLogout
                ) { innerPadding ->
                    UnidadesCurricularesProfessorScreen(
                        viewModel = screenViewModel,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}
