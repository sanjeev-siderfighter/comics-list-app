package com.siderfighter.comicsinfo.presentation.views.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListItemModel
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListModel
import javax.inject.Inject

class RajComicsListDiffUtil
@Inject
constructor() : DiffUtil.ItemCallback<RajComicsListItemModel>() {
    override fun areItemsTheSame(
        oldItem: RajComicsListItemModel,
        newItem: RajComicsListItemModel
    ): Boolean {
        return oldItem.comicNumber == newItem.comicNumber
    }

    override fun areContentsTheSame(
        oldItem: RajComicsListItemModel,
        newItem: RajComicsListItemModel
    ): Boolean {
        return oldItem == newItem
    }
}