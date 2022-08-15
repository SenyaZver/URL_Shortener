package com.example.urlshortener.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.urlshortener.data.model.URL


@Database(entities = [URL::class], version = 1)
abstract class URLdataBase: RoomDatabase() {
    abstract fun urlDao(): URLdao

}