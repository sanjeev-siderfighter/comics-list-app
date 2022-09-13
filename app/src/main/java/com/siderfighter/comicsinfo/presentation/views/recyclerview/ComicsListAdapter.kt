package com.siderfighter.comicsinfo.presentation.views.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.siderfighter.comicsinfo.databinding.ComicItemLayoutBinding
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListItemModel
import javax.inject.Inject

class ComicsListAdapter
@Inject
constructor(rajComicsListDiffUtil: RajComicsListDiffUtil) :
    ListAdapter<RajComicsListItemModel, ComicsListViewHolder>(rajComicsListDiffUtil) {

    private lateinit var itemClickListener: ItemClickListener

    fun setComicsList(comicsList: List<RajComicsListItemModel>) {
        submitList(comicsList)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsListViewHolder {
        return ComicsListViewHolder(
            binding = ComicItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            itemClickListener = itemClickListener
        )
    }

    override fun onBindViewHolder(holder: ComicsListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface ItemClickListener {
        fun onItemClick(rajComicsItem: RajComicsListItemModel)
    }
}