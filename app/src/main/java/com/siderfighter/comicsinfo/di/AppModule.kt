package com.siderfighter.comicsinfo.di

import com.siderfighter.comicsinfo.data.repository.rajcomics.RajComicsRepository
import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindsRajComicsRepository(rajComicsRepository: RajComicsRepository): IRajComicsRepository
}