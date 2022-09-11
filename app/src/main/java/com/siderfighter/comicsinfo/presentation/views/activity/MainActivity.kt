package com.siderfighter.comicsinfo.presentation.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.siderfighter.comicsinfo.R
import com.siderfighter.comicsinfo.presentation.viewmodel.RajComicsViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject lateinit var viewModel: RajComicsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getRajComicsPage1()
    }
}