package com.senai.carteirinhavini.feature.unidadeCurricular.data


import com.senai.carteirinhavini.feature.unidadeCurricular.domain.model.UnidadeCurricular
import kotlin.String

fun dataSource(): List<UnidadeCurricular> {
    return listOf(
        UnidadeCurricular(
            id = "1",
            nome = "Biologia",
            professor = "Fernanda",
            nota1 = 8.7,
            nota2 = 9.1,
            media = 8.9,
            faltas = 2
        ),
        UnidadeCurricular(
            id = "2",
            nome = "Geografia",
            professor = "Carlos",
            nota1 = 6.5,
            nota2 = 7.3,
            media = 6.9,
            faltas = 4
        ),
        UnidadeCurricular(
            id = "3",
            nome = "Química",
            professor = "Patrícia",
            nota1 = 9.0,
            nota2 = 8.4,
            media = 8.7,
            faltas = 1
        ),
        UnidadeCurricular(
            id = "4",
            nome = "Física",
            professor = "Roberto",
            nota1 = 7.2,
            nota2 = 6.8,
            media = 7.0,
            faltas = 3
        ),
        UnidadeCurricular(
            id = "5",
            nome = "Programação",
            professor = "Juliana",
            nota1 = 9.5,
            nota2 = 9.8,
            media = 9.6,
            faltas = 0
        ),
        UnidadeCurricular(
            id = "6",
            nome = "Redação",
            professor = "Marcelo",
            nota1 = 8.0,
            nota2 = 7.5,
            media = 7.8,
            faltas = 2
        ),
        UnidadeCurricular(
            id = "7",
            nome = "Artes",
            professor = "Camila",
            nota1 = 8.9,
            nota2 = 9.3,
            media = 9.1,
            faltas = 1
        )
    )
}