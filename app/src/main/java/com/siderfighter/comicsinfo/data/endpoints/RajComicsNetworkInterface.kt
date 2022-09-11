package com.siderfighter.comicsinfo.data.endpoints

import com.siderfighter.comicsinfo.data.endpoints.ApiEndpointConstants.RAJ_COMICS_PAGE_1
import retrofit2.Response
import retrofit2.http.GET

interface RajComicsNetworkInterface {
    @GET(RAJ_COMICS_PAGE_1)
    suspend fun getRajComicsPage1(): Response<String> // todo: Change to appropriate object
}