package com.siderfighter.comicsinfo.data.endpoints

import com.siderfighter.comicsinfo.data.endpoints.ApiEndpointConstants.RAJ_COMICS_LIST
import com.siderfighter.comicsinfo.data.repository.rajcomics.RajComicsPageRequest
import com.siderfighter.comicsinfo.data.repository.rajcomics.RajComicsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RajComicsNetworkInterface {
    @GET(RAJ_COMICS_LIST)
    suspend fun getAllRajComics(): RajComicsResponse // todo: Change to appropriate object

    @POST(RAJ_COMICS_LIST)
    suspend fun getRajComicsByPage(
        @Body rajComicsPageRequest: RajComicsPageRequest
    ) : RajComicsResponse
}