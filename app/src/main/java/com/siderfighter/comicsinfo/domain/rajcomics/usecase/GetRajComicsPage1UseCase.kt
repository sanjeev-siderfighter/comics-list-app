package com.siderfighter.comicsinfo.domain.rajcomics.usecase

import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsRepository
import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRajComicsPage1UseCase @Inject constructor(
    private val rajComicsRepository: IRajComicsRepository
) {
//    @Inject lateinit var rajComicsRepository: IRajComicsRepository
    suspend fun invokeUseCase(): IRajComicsResponse {
        return withContext(Dispatchers.IO) {
            rajComicsRepository.getRajComicsPage1()
        }
    }
}