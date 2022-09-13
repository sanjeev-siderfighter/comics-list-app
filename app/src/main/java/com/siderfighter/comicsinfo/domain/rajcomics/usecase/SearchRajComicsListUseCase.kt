package com.siderfighter.comicsinfo.domain.rajcomics.usecase

import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRajComicsListUseCase
@Inject
constructor() {
    suspend fun invokeUseCase(
        rajComicsList: RajComicsListModel,
        key: String
    ): Flow<RajComicsListModel> {
        return flow {
            emit(
                filterRajComicsByCharacter(
                    rajComicsList = rajComicsList,
                    key = key
                )
            )
        }
    }

    private suspend fun filterRajComicsByCharacter(
        rajComicsList: RajComicsListModel,
        key: String
    ): RajComicsListModel {
        return withContext(Dispatchers.Default) {
            RajComicsListModel(
                rajComicsList = rajComicsList.rajComicsList.filter {
                    it.comicName.contains(key, ignoreCase = true)
                            || it.characterName.contains(key, ignoreCase = true)
                            || it.comicNumber.contains(key, ignoreCase = true)
                }
            )
        }
    }
}