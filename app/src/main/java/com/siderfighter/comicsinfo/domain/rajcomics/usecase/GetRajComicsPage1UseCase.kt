package com.siderfighter.comicsinfo.domain.rajcomics.usecase

import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsRepository
import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetRajComicsPage1UseCase(
    private val rajComicsRepository: IRajComicsRepository
) {
    suspend fun invokeUseCase(): IRajComicsResponse {
        return withContext(Dispatchers.IO) {
            rajComicsRepository.getRajComicsPage1()
        }
    }
}