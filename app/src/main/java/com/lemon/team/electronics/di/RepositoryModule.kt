package com.lemon.team.electronics.di

import com.lemon.team.electronics.data.Repository
import com.lemon.team.electronics.data.RepositoryImpl
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideHappySocialRepository(
        impl: RepositoryImpl
    ): Repository


}

