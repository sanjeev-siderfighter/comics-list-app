package com.siderfighter.comicsinfo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RajComicsDetailViewModel
@Inject
constructor() : ViewModel() {
    var rajComicsList: RajComicsListModel = RajComicsListModel(emptyList())
}