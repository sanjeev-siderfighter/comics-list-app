package com.siderfighter.comicsinfo.data.repository.rajcomics

import com.siderfighter.comicsinfo.data.endpoints.RajComicsNetworkInterface
import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsRepository
import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RajComicsRepository @Inject constructor(
    private val networkInterface: RajComicsNetworkInterface
) : IRajComicsRepository {
    //    @Inject lateinit var networkInterface: RajComicsNetworkInterface
    override suspend fun getAllRajComics(): IRajComicsResponse {
        return withContext(Dispatchers.IO) {
            networkInterface.getAllRajComics()
        }
    }

    override suspend fun getRajComicsByPage(page: Int): IRajComicsResponse {
        return withContext(Dispatchers.IO) {
            networkInterface.getRajComicsByPage(
                RajComicsPageRequest(
                    page = page
                )
            )
        }
    }


}