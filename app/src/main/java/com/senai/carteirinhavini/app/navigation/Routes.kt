package com.senai.carteirinhavini.app.navigation

import android.net.Uri

object Routes {
    const val Login = "login"
    const val Home = "home"
    const val Carteirinha = "carteirinha"
    const val Semestres = "semestres"
    const val NotasPresenca = "notas-presenca/{semestre}"
    const val UnidadeCurricular = "unidade-curricular"

    const val ProfessorHome = "professor-home"
    const val ProfessorTurmas = "professor-turmas"
    const val ProfessorUnidades = "professor-unidades/{turma}"
    const val ProfessorNotas = "professor-notas/{turma}/{unidade}"

    fun notasPresenca(semestre: Int) = "notas-presenca/$semestre"
    fun professorUnidades(turma: String) = "professor-unidades/${Uri.encode(turma)}"
    fun professorNotas(turma: String, unidade: String) =
        "professor-notas/${Uri.encode(turma)}/${Uri.encode(unidade)}"
}
