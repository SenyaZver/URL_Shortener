package com.example.urlshortener.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "URL_table")
data class URL(
    @PrimaryKey val address: String,
    val short_address: String?
)
