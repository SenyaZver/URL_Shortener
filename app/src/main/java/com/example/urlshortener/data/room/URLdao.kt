package com.example.urlshortener.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.urlshortener.data.model.URL

@Dao
interface URLdao {
    @Query("SELECT * FROM URL_table")
    suspend fun getAllURLs(): MutableList<URL>

    @Query("DELETE FROM URL_table")
    suspend fun clearDataBase()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertURL(url: URL)
}

