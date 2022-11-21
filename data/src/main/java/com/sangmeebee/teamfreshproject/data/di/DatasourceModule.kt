package com.sangmeebee.teamfreshproject.data.di

import com.sangmeebee.teamfreshproject.data.datasource.SignInDatasourceImpl
import com.sangmeebee.teamfreshproject.domain.datasource.SignInDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DatasourceModule {

    @Singleton
    @Binds
    abstract fun bindSignInDatasource(impl: SignInDatasourceImpl): SignInDatasource
}
