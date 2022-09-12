package com.siderfighter.comicsinfo.data.repository.rajcomics

import com.siderfighter.comicsinfo.data.endpoints.RajComicsNetworkInterface
import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsRepository
import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsResponse
import javax.inject.Inject

class RajComicsRepository @Inject constructor(
    private val networkInterface: RajComicsNetworkInterface
) : IRajComicsRepository {
//    @Inject lateinit var networkInterface: RajComicsNetworkInterface
    override suspend fun getRajComicsPage1(): IRajComicsResponse {
        return networkInterface.getRajComicsPage1()
    }

    override suspend fun getRajComicsByPage(page: Int): IRajComicsResponse {
        return networkInterface.getRajComicsByPage(
            RajComicsPageRequest(
                page = page
            )
        )
    }


}