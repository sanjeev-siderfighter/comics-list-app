package com.siderfighter.comicsinfo.presentation.views.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.siderfighter.comicsinfo.databinding.ComicItemLayoutBinding
import com.siderfighter.comicsinfo.presentation.models.RajComicsListItemModel
import javax.inject.Inject

class ComicsListAdapter
@Inject
constructor() : RecyclerView.Adapter<ComicsListViewHolder>() {

    private lateinit var comicsList: List<List<String>>

    fun setComicsList(comicsList: List<List<String>>) {
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
            rajComicsListItemModel = RajComicsListItemModel(
                comicName = comicsList[position][1],
                characterName = comicsList[position][3],
                comicNumber = comicsList[position][2]
            )
        )
    }

    override fun getItemCount(): Int {
        return comicsList.size
    }
}