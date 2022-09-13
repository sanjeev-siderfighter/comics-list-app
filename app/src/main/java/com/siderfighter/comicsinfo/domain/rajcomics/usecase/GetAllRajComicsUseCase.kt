package com.siderfighter.comicsinfo.domain.rajcomics.usecase

import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsRepository
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListItemModel
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class GetAllRajComicsUseCase @Inject constructor(
    @Named("rajComics")
    private val rajComicsRepository: IRajComicsRepository
) {
//    @Inject lateinit var rajComicsRepository: IRajComicsRepository
    suspend fun invokeUseCase(): Flow<RajComicsListModel> {
        return flow {
            val rajComicsList = rajComicsRepository.getAllRajComics()
            emit(parseRajComicsList(rajComicsList = rajComicsList.data))
        }
    }

    private suspend fun parseRajComicsList(rajComicsList: List<List<String>>): RajComicsListModel {
        return withContext(Dispatchers.Default) {
            RajComicsListModel(
                rajComicsList = rajComicsList.map { currComic ->
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