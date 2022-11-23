package com.sangmeebee.teamfreshproject.data.di

import com.sangmeebee.teamfreshproject.data.service.SignInAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object APIModule {

    @Singleton
    @Provides
    internal fun provideSignInAPI(retrofit: Retrofit): SignInAPI =
        retrofit.create(SignInAPI::class.java)
}
