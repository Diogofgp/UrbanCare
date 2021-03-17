package com.example.urbancare.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notas_table")

class Nota(

    @PrimaryKey(autoGenerate = true) val id: Int?= null,
    @ColumnInfo(name = "notas") val notas: String,
    @ColumnInfo(name = "descricao") val descricoes: String

)