package com.example.urlshortener.di

import android.content.Context
import androidx.room.Room
import com.example.urlshortener.data.room.URLdao
import com.example.urlshortener.data.room.URLdataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataBaseModule {
    @Provides
    @Singleton
    fun getURLdao(dataBase: URLdataBase): URLdao {
        return dataBase.urlDao()
    }
    @Provides
    @Singleton
    fun getDataBase(@ApplicationContext appContext: Context): URLdataBase {
        return Room.databaseBuilder(appContext, URLdataBase::class.java, "URLdatabase").build()
    }

}