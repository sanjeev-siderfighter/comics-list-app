package com.siderfighter.comicsinfo.presentation.views.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.siderfighter.comicsinfo.databinding.ComicItemLayoutBinding
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListItemModel

class ComicsListViewHolder(
    private val binding: ComicItemLayoutBinding,
    private val itemClickListener: ComicsListAdapter.ItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(rajComicsListItemModel: RajComicsListItemModel, position: Int) {
        binding.rajComicItem = rajComicsListItemModel

        binding.parentLayout.setOnClickListener {
            itemClickListener.onItemClick(
                rajComicsItem = rajComicsListItemModel,
                position = position
            )
        }
    }
}