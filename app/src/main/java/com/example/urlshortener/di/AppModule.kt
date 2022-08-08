package com.example.urlshortener.di

import com.example.urlshortener.data.DataProvider
import com.example.urlshortener.data.repository.URLrepositoryImpl
import com.example.urlshortener.domain.repository.URLrepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getDataProvider(): DataProvider {
        return DataProvider()
    }


    @Provides
    @Singleton
    fun getRepository(): URLrepository {
        return URLrepositoryImpl(getDataProvider())
    }




}
