package com.siderfighter.comicsinfo.domain.rajcomics

import com.siderfighter.comicsinfo.data.repository.rajcomics.RajComicsResponse

interface IRajComicsRepository {
    suspend fun getRajComicsPage1(): IRajComicsResponse
}