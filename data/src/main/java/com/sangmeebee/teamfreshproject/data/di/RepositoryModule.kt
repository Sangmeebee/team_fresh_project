package com.sangmeebee.teamfreshproject.data.di

import com.sangmeebee.teamfreshproject.data.repository.SignInRepositoryImpl
import com.sangmeebee.teamfreshproject.domain.repository.SignInRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSignInRepository(impl: SignInRepositoryImpl): SignInRepository
}
