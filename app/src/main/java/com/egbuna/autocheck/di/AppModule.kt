package com.egbuna.autocheck.di

import android.content.Context
import com.egbuna.autocheck.AutoCheckApplication
import com.egbuna.autocheck.BuildConfig
import com.egbuna.autocheck.data.MockHelper
import com.egbuna.autocheck.data.remote.AutoCheckApi
import com.egbuna.autocheck.repository.CarModelRepository
import com.egbuna.autocheck.repository.CarModelRepositoryImpl
import com.egbuna.autocheck.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSaltPayApi(client: OkHttpClient): AutoCheckApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(AutoCheckApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder()
            .readTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .retryOnConnectionFailure(false)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    }

    @Provides
    @Singleton
    fun provideTopCarRepository(mock: MockHelper, api: AutoCheckApi): CarModelRepository {
        return CarModelRepositoryImpl(mock, api)
    }

    @Provides
    @Singleton
    fun provideApplication(@ApplicationContext context: Context): AutoCheckApplication = context.applicationContext as AutoCheckApplication
}