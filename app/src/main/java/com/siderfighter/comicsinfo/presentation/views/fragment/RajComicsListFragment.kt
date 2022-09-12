package com.siderfighter.comicsinfo.presentation.views.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.siderfighter.comicsinfo.databinding.RajComicsListFragmentBinding
import com.siderfighter.comicsinfo.presentation.viewmodel.RajComicsListViewModel
import com.siderfighter.comicsinfo.presentation.views.recyclerview.ComicsListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RajComicsListFragment : Fragment() {

    private val viewModel: RajComicsListViewModel by viewModels()
    private lateinit var binding: RajComicsListFragmentBinding
    @Inject
    lateinit var rajComicsAdapter: ComicsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RajComicsListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()

        viewModel.getAllRajComics()
    }

    private fun initObservers() {
        viewModel.allRajComicsList.observe(viewLifecycleOwner) {
            Log.d("siderfighter", "$it")
            rajComicsAdapter.setComicsList(it.data)
            binding.rvComicsList.adapter = rajComicsAdapter
        }
    }

}