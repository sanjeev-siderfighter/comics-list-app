package com.siderfighter.comicsinfo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(): ViewModel()
{
    private val _rajComicsListLiveData = MutableLiveData<RajComicsListModel>()
    val rajComicsListLiveData: LiveData<RajComicsListModel> = _rajComicsListLiveData

    fun passRajComicsList(rajComicsList: RajComicsListModel) {
        _rajComicsListLiveData.value = rajComicsList
    }
}