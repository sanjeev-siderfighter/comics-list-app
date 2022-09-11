package com.siderfighter.comicsinfo.data.repository.rajcomics

import com.siderfighter.comicsinfo.data.endpoints.RajComicsNetworkInterface
import retrofit2.Response

class RajComicsRepository(
    private val networkInterface: RajComicsNetworkInterface
) {
    suspend fun getRajComicsPage1(): RajComicsResponse {
        return networkInterface.getRajComicsPage1()
    }
}