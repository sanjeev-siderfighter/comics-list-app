package com.siderfighter.comicsinfo.presentation.views.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.siderfighter.comicsinfo.databinding.ComicItemLayoutBinding
import com.siderfighter.comicsinfo.presentation.models.RajComicsListItemModel

class ComicsListViewHolder(private val binding: ComicItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(rajComicsListItemModel: RajComicsListItemModel) {
        binding.rajComicItem = rajComicsListItemModel
    }
}