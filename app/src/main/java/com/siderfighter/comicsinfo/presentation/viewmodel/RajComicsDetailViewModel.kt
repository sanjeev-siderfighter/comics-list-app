package com.siderfighter.comicsinfo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListItemModel
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListModel
import com.siderfighter.comicsinfo.domain.rajcomics.usecase.GetRajComicsListOfCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RajComicsDetailViewModel
@Inject
constructor(
    private val getRajComicsListOfCharacterUseCase: GetRajComicsListOfCharacterUseCase
) : ViewModel() {
    var rajComicsList: RajComicsListModel = RajComicsListModel(emptyList())
    private var rajComicsCharacterList = RajComicsListModel(emptyList())
    private var currentPosition: Int = 0

    private val _rajComic = MutableLiveData<RajComicsListItemModel>()
    val rajComic: LiveData<RajComicsListItemModel> = _rajComic

    fun setInitialComic(rajComic: RajComicsListItemModel) {
        _rajComic.value = rajComic
    }

    fun getNextComicCharacterWise() {
        sendRajComicsList(rajComic = rajComicsCharacterList.rajComicsList[++currentPosition])
    }

    fun getPreviousComicCharacterWise() {
        sendRajComicsList(rajComic = rajComicsCharacterList.rajComicsList[--currentPosition])
    }

    fun fetchRajComicsListByCharacter(initialPosition: Int) {
        viewModelScope.launch {
            getRajComicsListOfCharacterUseCase.invokeUseCase(
                rajComicsList,
                rajComicsList.rajComicsList[initialPosition].characterName
            )
                .onStart {
                    // showLoader()
                }
                .onCompletion {
                    // hideLoader
                }
                .collect { rajComicsList ->
                    rajComicsCharacterList = rajComicsList
                }
        }
    }

    private fun sendRajComicsList(rajComic: RajComicsListItemModel) {
        _rajComic.postValue(rajComic)
    }
}