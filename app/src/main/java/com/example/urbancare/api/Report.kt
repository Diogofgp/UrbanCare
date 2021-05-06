package com.example.urbancare.api

data class Report(

    val titulo: String,
    val descricao: String,
    val latitude: Double,
    val longitude: Double,
    val fotografia: String,
    val tipo: Int,
    val users_id: Int
)