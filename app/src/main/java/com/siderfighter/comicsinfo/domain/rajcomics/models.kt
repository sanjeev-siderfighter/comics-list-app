package com.siderfighter.comicsinfo.domain.rajcomics

interface IRajComicsResponse {
    val data: List<List<String>>
    val nextPage: Int
}

interface IRajComicsPageRequest {
    val page: Int
}