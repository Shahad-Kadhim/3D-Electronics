package com.lemon.team.electronics.di

import com.lemon.team.electronics.data.ElectronicRepository
import com.lemon.team.electronics.data.ElectronicRepositoryImpl
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provide3DElectronicRepository(
        impl: ElectronicRepositoryImpl
    ): ElectronicRepository


}

