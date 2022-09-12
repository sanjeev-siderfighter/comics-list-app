package com.siderfighter.comicsinfo.data.repository.rajcomics

import com.google.gson.annotations.SerializedName
import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsPageRequest
import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsResponse

data class RajComicsResponse(
    @SerializedName("rajComicsList")
    override val data: List<List<String>>,
    @SerializedName("nextPage")
    override val nextPage: Int
) : IRajComicsResponse

data class RajComicsPageRequest(
    @SerializedName("page")
    override val page: Int
) : IRajComicsPageRequest
