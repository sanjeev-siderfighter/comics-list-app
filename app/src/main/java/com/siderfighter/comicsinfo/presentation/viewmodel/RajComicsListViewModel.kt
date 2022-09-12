package com.siderfighter.comicsinfo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsResponse
import com.siderfighter.comicsinfo.domain.rajcomics.usecase.GetAllRajComicsUseCase
import com.siderfighter.comicsinfo.domain.rajcomics.usecase.GetRajComicsByPageUseCase
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
    private val getRajComicsByPageUseCase: GetRajComicsByPageUseCase
) : ViewModel() {

    private val _allRajComicsList = MutableLiveData<IRajComicsResponse>()
    val allRajComicsList: LiveData<IRajComicsResponse> = _allRajComicsList

    fun getAllRajComics() {
        viewModelScope.launch {
            getAllRajComicsUseCase.invokeUseCase()
                .onStart {
                    Log.d("siderfighter", "getAllRajComicsUseCase.invokeUseCase() onStart")
                    // todo: show progress
                }
                .onCompletion {
                    Log.d("siderfighter", "getAllRajComicsUseCase.invokeUseCase() onCompletion")
                    // todo: hide progress
                }
                .collect {
                    _allRajComicsList.postValue(it)
                }
        }
    }
}