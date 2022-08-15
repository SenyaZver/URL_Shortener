package com.example.urlshortener.di

import android.content.Context
import androidx.room.Room
import com.example.urlshortener.common.Constants.BASE_URL
import com.example.urlshortener.data.remote.DataProvider
import com.example.urlshortener.data.repository.URLrepositoryImpl
import com.example.urlshortener.data.remote.CleanUriApi
import com.example.urlshortener.data.room.URLdao
import com.example.urlshortener.data.room.URLdataBase
import com.example.urlshortener.domain.AddressFormatter
import com.example.urlshortener.domain.repository.URLrepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getDataProvider(): DataProvider {
        return DataProvider(getCleanUriApi())
    }


    @Provides
    @Singleton
    fun getRepository(dataProvider: DataProvider, dao: URLdao): URLrepository {
        return URLrepositoryImpl(dataProvider, dao)
    }

    @Provides
    @Singleton
    fun getFormatter(): AddressFormatter {
        return AddressFormatter()
    }

    @Provides
    @Singleton
    fun getCleanUriApi(): CleanUriApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CleanUriApi::class.java)
    }





}
