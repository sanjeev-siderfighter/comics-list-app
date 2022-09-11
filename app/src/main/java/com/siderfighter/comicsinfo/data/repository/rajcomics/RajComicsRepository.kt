package com.siderfighter.comicsinfo.data.repository.rajcomics

import com.siderfighter.comicsinfo.data.endpoints.RajComicsNetworkInterface
import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsRepository
import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsResponse

class RajComicsRepository(
    private val networkInterface: RajComicsNetworkInterface
) : IRajComicsRepository {
    override suspend fun getRajComicsPage1(): IRajComicsResponse {
        return networkInterface.getRajComicsPage1()
    }
}