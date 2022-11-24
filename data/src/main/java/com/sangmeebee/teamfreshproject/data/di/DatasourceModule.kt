package com.sangmeebee.teamfreshproject.data.di

import com.sangmeebee.teamfreshproject.data.datasource.BoardRemoteDatasource
import com.sangmeebee.teamfreshproject.data.datasource.BoardRemoteDatasourceImpl
import com.sangmeebee.teamfreshproject.data.datasource.SignInRemoteDatasource
import com.sangmeebee.teamfreshproject.data.datasource.SignInRemoteDatasourceImpl
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
    abstract fun bindSignInRemoteDatasource(impl: SignInRemoteDatasourceImpl): SignInRemoteDatasource

    @Singleton
    @Binds
    abstract fun bindBoardRemoteDatasource(impl: BoardRemoteDatasourceImpl): BoardRemoteDatasource
}
