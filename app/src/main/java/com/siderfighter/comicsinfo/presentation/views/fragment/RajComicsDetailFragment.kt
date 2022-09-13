package com.siderfighter.comicsinfo.presentation.views.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.siderfighter.comicsinfo.R
import com.siderfighter.comicsinfo.databinding.RajComicsDetailFragmentBinding
import com.siderfighter.comicsinfo.presentation.viewmodel.RajComicsDetailViewModel

class RajComicsDetailFragment : Fragment() {

    private lateinit var viewModel: RajComicsDetailViewModel
    private lateinit var binding: RajComicsDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RajComicsDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}