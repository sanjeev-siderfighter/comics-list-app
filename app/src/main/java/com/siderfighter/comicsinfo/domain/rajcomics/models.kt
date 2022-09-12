package com.siderfighter.comicsinfo.domain.rajcomics

interface IRajComicsResponse {
    val data: List<List<String>>
    val nextPage: Int
}

interface IRajComicsPageRequest {
    val page: Int
}

data class RajComicsListItemModel(
    val comicName: String,
    val characterName: String,
    val comicNumber: String
)

data class RajComicsListModel(
    val rajComicsList: List<RajComicsListItemModel>
)