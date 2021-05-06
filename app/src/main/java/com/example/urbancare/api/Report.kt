package com.example.urbancare.api

data class Report(
    val titulo: String,
    val descricao: String,
    val latitude: String,
    val longitude: String,
    val fotografia: String,
    val tipo: String,
    val users_id: String
)