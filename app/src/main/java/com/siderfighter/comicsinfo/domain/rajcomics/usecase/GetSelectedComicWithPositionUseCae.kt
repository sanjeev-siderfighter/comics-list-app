package com.siderfighter.comicsinfo.domain.rajcomics.usecase

import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListItemModel
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSelectedComicWithPositionUseCae
@Inject
constructor(private val getRajComicsListOfCharacterUseCase: GetRajComicsListOfCharacterUseCase) {

    suspend fun invokeUseCase(
        allRajComics: RajComicsListModel,
        selectedComic: RajComicsListItemModel
    ): Flow<Pair<RajComicsListModel, Int>> {
        return withContext(Dispatchers.Default) {
            flow {
                getRajComicsListOfCharacterUseCase.invokeUseCase(
                    allRajComics,
                    selectedComic.characterName
                )
                    .collect { characterComicsList ->
                        emit(
                            Pair(
                                first = characterComicsList,
                                second = characterComicsList.rajComicsList.indexOf(selectedComic)
                            )
                        )
                    }
            }
        }
    }
}