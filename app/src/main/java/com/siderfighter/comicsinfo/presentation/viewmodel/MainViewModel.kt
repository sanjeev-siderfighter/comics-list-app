package com.siderfighter.comicsinfo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(): ViewModel()
{
    private val _rajComicsListFlow = MutableStateFlow<RajComicsListModel?>(null)
    val rajComicsListFlow: StateFlow<RajComicsListModel?> = _rajComicsListFlow

    fun passRajComicsList(rajComicsList: RajComicsListModel) {
        _rajComicsListFlow.value = rajComicsList
    }
}