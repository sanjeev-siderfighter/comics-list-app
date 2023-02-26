package com.siderfighter.comicsinfo.domain.rajcomics.usecase

import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsRepository
import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsResponse
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListItemModel
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class GetRajComicsByPageUseCase @Inject constructor(
    @Named("rajComics")
    private val rajComicsRepository: IRajComicsRepository
) {
    suspend fun invokeUseCase(page: Int): RajComicsListModel {
        return withContext(Dispatchers.IO) {
            parseRajComicsResponse(response = rajComicsRepository.getRajComicsByPage(page))
        }
    }

    private suspend fun parseRajComicsResponse(response: IRajComicsResponse): RajComicsListModel {
        return withContext(Dispatchers.Default) {
            RajComicsListModel(
                rajComicsList = response.data.map { currComic ->
                    RajComicsListItemModel(
                        comicName = currComic[1].trim(),
                        characterName = currComic[3].trim(),
                        comicNumber = currComic[2].trim()
                    )

                }
            )
        }
    }
}