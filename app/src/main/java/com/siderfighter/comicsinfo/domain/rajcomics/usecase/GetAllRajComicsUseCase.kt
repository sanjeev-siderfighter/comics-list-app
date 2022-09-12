package com.siderfighter.comicsinfo.domain.rajcomics.usecase

import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsRepository
import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named

class GetAllRajComicsUseCase @Inject constructor(
    @Named("rajComics")
    private val rajComicsRepository: IRajComicsRepository
) {
//    @Inject lateinit var rajComicsRepository: IRajComicsRepository
    suspend fun invokeUseCase(): Flow<IRajComicsResponse> {
        return flow {
            emit(rajComicsRepository.getAllRajComics())
        }
    }
}