package com.siderfighter.comicsinfo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListItemModel
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListModel
import com.siderfighter.comicsinfo.domain.rajcomics.usecase.GetSelectedComicWithPositionUseCae
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
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

    private val _rajComic = MutableLiveData<RajComicsListItemModel>()
    val rajComic: LiveData<RajComicsListItemModel> = _rajComic

    private val _shouldShowToast = MutableLiveData<Boolean>()
    val shouldShowToast: LiveData<Boolean> = _shouldShowToast

    fun setInitialComic(rajComic: RajComicsListItemModel) {
        _rajComic.value = rajComic
    }

    fun fetchRajComicsListByCharacter(initialPosition: Int) {
        viewModelScope.launch {
            getSelectedComicWithPositionUseCae.invokeUseCase(
                rajComicsList,
                rajComicsList.rajComicsList[initialPosition]
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

    fun getNextComicCharacterWise() {
        if (isAdditionSafe()) {
            sendRajComicsList(rajComic = rajComicsCharacterList.rajComicsList[++currentPosition])
        } else {
            _shouldShowToast.value = true
        }
    }

    fun getPreviousComicCharacterWise() {
        if (isSubtractionSafe()) {
            sendRajComicsList(rajComic = rajComicsCharacterList.rajComicsList[--currentPosition])
        } else {
            _shouldShowToast.value = false
        }
    }

    private fun isAdditionSafe(): Boolean = (currentPosition + 1) < rajComicsCharacterList.rajComicsList.size

    private fun isSubtractionSafe(): Boolean = currentPosition - 1 >= 0

    private fun sendRajComicsList(rajComic: RajComicsListItemModel) {
        _rajComic.postValue(rajComic)
    }
}