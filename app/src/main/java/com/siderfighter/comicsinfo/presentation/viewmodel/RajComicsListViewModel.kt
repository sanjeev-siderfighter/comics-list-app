package com.siderfighter.comicsinfo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListModel
import com.siderfighter.comicsinfo.domain.rajcomics.usecase.GetAllRajComicsUseCase
import com.siderfighter.comicsinfo.domain.rajcomics.usecase.GetRajComicsByPageUseCase
import com.siderfighter.comicsinfo.domain.rajcomics.usecase.SearchRajComicsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RajComicsListViewModel
@Inject
constructor(
    private val getAllRajComicsUseCase: GetAllRajComicsUseCase,
    private val getRajComicsByPageUseCase: GetRajComicsByPageUseCase,
    private val searchRajComicsListUseCase: SearchRajComicsListUseCase
) : ViewModel() {

    private var allRajComics = RajComicsListModel(listOf())

    private val _allRajComicsList = MutableLiveData<RajComicsListModel>()
    val allRajComicsList: LiveData<RajComicsListModel> = _allRajComicsList

    private val _shouldShowLoader = MutableLiveData<Boolean>()
    val shouldShowLoader: LiveData<Boolean> = _shouldShowLoader

    val searchKey = MutableLiveData<String>()

    fun getAllRajComics() {
        viewModelScope.launch {
            getAllRajComicsUseCase.invokeUseCase()
                .onStart {
                    Log.d("siderfighter", "getAllRajComicsUseCase.invokeUseCase() onStart")
                    showLoader()
                }
                .onCompletion {
                    Log.d("siderfighter", "getAllRajComicsUseCase.invokeUseCase() onCompletion")
                    hideLoader()
                }
                .collect {
                    allRajComics = it
                    _allRajComicsList.postValue(allRajComics)
                }
        }
    }

    fun searchRajComicsList(key: String) {
        if (key.trim().isBlank()) {
            _allRajComicsList.postValue(allRajComics)
            return
        }

        viewModelScope.launch {

            searchRajComicsListUseCase.invokeUseCase(
                rajComicsList = allRajComics,
                key = key
            ).onStart {
                showLoader()
            }.onCompletion {
                hideLoader()
            }.collect {
                _allRajComicsList.postValue(it)
            }
        }
    }

    private fun showLoader() {
        _shouldShowLoader.postValue(true)
    }

    private fun hideLoader() {
        _shouldShowLoader.postValue(false)
    }
}