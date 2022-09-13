package com.siderfighter.comicsinfo.domain.rajcomics.usecase

import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRajComicsListByCharacterUseCase
@Inject
constructor() {
    suspend fun invokeUseCase(
        rajComicsList: RajComicsListModel,
        character: String
    ): Flow<RajComicsListModel> {
        return flow {
            emit(
                filterRajComicsByCharacter(
                    rajComicsList = rajComicsList,
                    character = character
                )
            )
        }
    }

    private suspend fun filterRajComicsByCharacter(
        rajComicsList: RajComicsListModel,
        character: String
    ): RajComicsListModel {
        return withContext(Dispatchers.Default) {
            RajComicsListModel(
                rajComicsList = rajComicsList.rajComicsList.filter {
                    it.characterName.equals(character, ignoreCase = true)
                }
            )
        }
    }
}