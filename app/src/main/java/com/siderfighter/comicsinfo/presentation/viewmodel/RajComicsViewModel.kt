package com.siderfighter.comicsinfo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsResponse
import com.siderfighter.comicsinfo.domain.rajcomics.usecase.GetRajComicsPage1UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class RajComicsViewModel @Inject constructor(
    private val getRajComicsPage1UseCase: GetRajComicsPage1UseCase
) : ViewModel() {

//    @Inject lateinit var getRajComicsPage1UseCase: GetRajComicsPage1UseCase

    private val _rajComicsPage1 = MutableLiveData<IRajComicsResponse>() // todo: Replace with flow
    val rajComicsPage1: LiveData<IRajComicsResponse> = _rajComicsPage1

    fun getRajComicsPage1() {
        viewModelScope.launch {
            val data = getRajComicsPage1UseCase.invokeUseCase()
            _rajComicsPage1.postValue(data)
        }
    }
}