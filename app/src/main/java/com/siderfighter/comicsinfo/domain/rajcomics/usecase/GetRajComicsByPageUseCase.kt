package com.siderfighter.comicsinfo.domain.rajcomics.usecase

import com.siderfighter.comicsinfo.data.repository.rajcomics.RajComicsResponse
import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsRepository
import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsResponse
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListItemModel
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class GetRajComicsByPageUseCase @Inject constructor(
    @Named("rajComics")
    private val rajComicsRepository: IRajComicsRepository
) {
    suspend fun invokeUseCase(page: Int): Flow<RajComicsListModel> {
        return flow {
            val rajComicsList = rajComicsRepository.getRajComicsByPage(page = page)
            emit(parseRajComicsList(rajComicsList = rajComicsList))
        }
//        return withContext(Dispatchers.IO) {
//            rajComicsRepository.getRajComicsByPage(page)
//        }
    }

    private suspend fun parseRajComicsList(
        rajComicsList: IRajComicsResponse,
    ): RajComicsListModel {
        return withContext(Dispatchers.Default) {
            RajComicsListModel(
                rajComicsList = rajComicsList.data.map { currComic ->
                    RajComicsListItemModel(
                        comicName = currComic[1].trim(),
                        characterName = currComic[3].trim(),
                        comicNumber = currComic[2].trim()
                    )
                },
                nextPage = rajComicsList.nextPage
            )
        }
    }
}