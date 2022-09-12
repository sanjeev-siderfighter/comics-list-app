package com.siderfighter.comicsinfo.domain.rajcomics

import com.siderfighter.comicsinfo.data.repository.rajcomics.RajComicsResponse

interface IRajComicsRepository {
    suspend fun getAllRajComics(): IRajComicsResponse
    suspend fun getRajComicsByPage(page: Int): IRajComicsResponse
}