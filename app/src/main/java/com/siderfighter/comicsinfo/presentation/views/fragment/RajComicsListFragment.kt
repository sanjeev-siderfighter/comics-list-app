package com.siderfighter.comicsinfo.presentation.views.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.siderfighter.comicsinfo.databinding.RajComicsListFragmentBinding
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListItemModel
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListModel
import com.siderfighter.comicsinfo.presentation.viewmodel.MainViewModel
import com.siderfighter.comicsinfo.presentation.viewmodel.RajComicsListViewModel
import com.siderfighter.comicsinfo.presentation.views.recyclerview.ComicsListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RajComicsListFragment : Fragment(), ComicsListAdapter.ItemClickListener {

    private val viewModel: RajComicsListViewModel by viewModels()
    private val sharedViewModel: MainViewModel by activityViewModels()

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
        setupBinding()
        initClickListeners()
        initObservers()

        viewModel.getAllRajComics()
    }

    private fun setupBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initClickListeners() {
        binding.btnEntireList.setOnClickListener {
            getRajComicsListOfCharacter(binding.etSearchBox.text.toString())
        }
    }

    // use collect when every state matters
    // use collectLatest when only the last state matters
    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.allRajComicsList.collect { rajComicsList ->
                        rajComicsList?.let {
                            initComicsListAdapter(it)
                        }
                    }
                }

                launch {
                    viewModel.shouldShowLoader.collect {
                        showOrHideLoader(it)
                    }
                }

                launch {
                    viewModel.searchKey.collect { searchKey ->
                        searchKey?.let {
                            viewModel.searchRajComicsList(it)
                        }
                    }
                }
            }
        }
    }

    private fun initComicsListAdapter(rajComicsListObject: RajComicsListModel) {
        Log.d("siderfighter", "$rajComicsListObject")
        binding.rvComicsList.adapter = rajComicsAdapter.apply {
            setComicsList(rajComicsListObject.rajComicsList)
            setItemClickListener(this@RajComicsListFragment)
        }
    }

    private fun showOrHideLoader(shouldShow: Boolean) {
        binding.loader.visibility = if (shouldShow) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun getRajComicsListOfCharacter(character: String) {
        viewModel.searchRajComicsList(character)
    }

    override fun onItemClick(rajComicsItem: RajComicsListItemModel, position: Int) {
        sharedViewModel.passRajComicsList(viewModel.allRajComics)

        val action =
            RajComicsListFragmentDirections.actionRajComicsListFragmentToRajComicsDetailFragment(
                comicTitle = rajComicsItem.comicName,
                comicNumber = rajComicsItem.comicNumber,
                characterName = rajComicsItem.characterName,
                initialPosition = position
            )
        findNavController().navigate(action)
    }

}