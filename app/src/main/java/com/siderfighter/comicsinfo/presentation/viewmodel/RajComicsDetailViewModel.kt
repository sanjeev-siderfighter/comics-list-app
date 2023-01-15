package com.siderfighter.comicsinfo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siderfighter.comicsinfo.R
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListItemModel
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListModel
import com.siderfighter.comicsinfo.domain.rajcomics.usecase.GetSelectedComicWithPositionUseCae
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RajComicsDetailViewModel
@Inject
constructor(
    private val getSelectedComicWithPositionUseCae: GetSelectedComicWithPositionUseCae
) : ViewModel() {
    var rajComicsList: RajComicsListModel = RajComicsListModel(emptyList())
    private var rajComicsCharacterList = RajComicsListModel(emptyList())
    private var currentPosition: Int = 0

    private val _rajComic = MutableStateFlow<RajComicsListItemModel?>(null)
    val rajComic: StateFlow<RajComicsListItemModel?> = _rajComic

    private val _shouldShowToast =
        MutableSharedFlow<Pair<Int, String>>(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    val shouldShowToast: SharedFlow<Pair<Int, String>> = _shouldShowToast

    fun setInitialComic(rajComic: RajComicsListItemModel) {
        _rajComic.value = rajComic
    }

    fun fetchRajComicsListByCharacter() {
        viewModelScope.launch {
            rajComic.value?.let { initialComic ->
                getSelectedComicWithPositionUseCae.invokeUseCase(
                    rajComicsList,
                    initialComic
                )
                    .onStart {
                        // showLoader()
                    }
                    .onCompletion {
                        // hideLoader
                    }
                    .collect { comicListWithPosition ->
                        rajComicsCharacterList = comicListWithPosition.first
                        currentPosition = comicListWithPosition.second
                    }
            }
        }
    }

    fun getNextComicCharacterWise(character: String) {
        if (isAdditionSafe()) {
            sendRajComicsList(rajComic = rajComicsCharacterList.rajComicsList[++currentPosition])
        } else {
            _shouldShowToast.tryEmit(Pair(R.string.last_comic_toast, character))
        }
    }

    fun getPreviousComicCharacterWise(character: String) {
        if (isSubtractionSafe()) {
            sendRajComicsList(rajComic = rajComicsCharacterList.rajComicsList[--currentPosition])
        } else {
            _shouldShowToast.tryEmit(Pair(R.string.first_comic_toast, character))
        }
    }

    private fun isAdditionSafe(): Boolean =
        (currentPosition + 1) < rajComicsCharacterList.rajComicsList.size

    private fun isSubtractionSafe(): Boolean = currentPosition - 1 >= 0

    private fun sendRajComicsList(rajComic: RajComicsListItemModel) {
        _rajComic.value = rajComic
    }
}