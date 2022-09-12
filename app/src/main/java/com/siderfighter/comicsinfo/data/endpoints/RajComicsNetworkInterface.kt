package com.siderfighter.comicsinfo.data.endpoints

import com.siderfighter.comicsinfo.data.endpoints.ApiEndpointConstants.RAJ_COMICS_PAGE_1
import com.siderfighter.comicsinfo.data.repository.rajcomics.RajComicsPageRequest
import com.siderfighter.comicsinfo.data.repository.rajcomics.RajComicsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RajComicsNetworkInterface {
    @GET(RAJ_COMICS_PAGE_1)
    suspend fun getRajComicsPage1(): RajComicsResponse // todo: Change to appropriate object

    @POST(RAJ_COMICS_PAGE_1)
    suspend fun getRajComicsByPage(
        @Body rajComicsPageRequest: RajComicsPageRequest
    ) : RajComicsResponse
}