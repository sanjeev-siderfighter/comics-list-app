package com.siderfighter.comicsinfo.presentation.views.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.siderfighter.comicsinfo.R
import com.siderfighter.comicsinfo.databinding.RajComicsListFragmentBinding
import com.siderfighter.comicsinfo.presentation.viewmodel.RajComicsListViewModel
import javax.inject.Inject

class RajComicsListFragment : Fragment() {

    private val viewModel: RajComicsListViewModel by viewModels()
    private lateinit var binding: RajComicsListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RajComicsListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}