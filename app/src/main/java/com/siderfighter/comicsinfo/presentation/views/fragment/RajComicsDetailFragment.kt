package com.siderfighter.comicsinfo.presentation.views.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.siderfighter.comicsinfo.databinding.RajComicsDetailFragmentBinding
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListItemModel
import com.siderfighter.comicsinfo.presentation.viewmodel.MainViewModel
import com.siderfighter.comicsinfo.presentation.viewmodel.RajComicsDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RajComicsDetailFragment : Fragment() {

    private val viewModel: RajComicsDetailViewModel by viewModels()
    private val sharedViewModel: MainViewModel by activityViewModels()

    private lateinit var binding: RajComicsDetailFragmentBinding
    private val args by navArgs<RajComicsDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RajComicsDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        initObservers()
        initClickListeners()
    }

    private fun initClickListeners() {
        binding.animNextButton.setOnClickListener {
            viewModel.getNextComicCharacterWise()
        }

        binding.animPreviousButton.setOnClickListener {
            viewModel.getPreviousComicCharacterWise()
        }
    }

    private fun initObservers() {
        sharedViewModel.rajComicsListLiveData.observe(viewLifecycleOwner) {
            Log.d("siderfighter", "observed shared view model")
            viewModel.rajComicsList = it
            viewModel.fetchRajComicsListByCharacter(args.initialPosition)
        }
    }

    private fun setupUi() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.setInitialComic(
            RajComicsListItemModel(
                comicNumber = args.comicNumber,
                comicName = args.comicTitle,
                characterName = args.characterName
            )
        )
    }
}