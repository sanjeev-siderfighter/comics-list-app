package com.siderfighter.comicsinfo.presentation.views.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.siderfighter.comicsinfo.databinding.ComicItemLayoutBinding
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListItemModel
import javax.inject.Inject

class ComicsListAdapter
@Inject
constructor() : RecyclerView.Adapter<ComicsListViewHolder>() {

    private lateinit var comicsList: List<RajComicsListItemModel>

    fun setComicsList(comicsList: List<RajComicsListItemModel>) {
        this.comicsList = comicsList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsListViewHolder {
        return ComicsListViewHolder(
            binding = ComicItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ComicsListViewHolder, position: Int) {
        holder.bind(
            comicsList[position]
        )
    }

    override fun getItemCount(): Int {
        return comicsList.size
    }
}