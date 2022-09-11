package com.siderfighter.comicsinfo.presentation.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.siderfighter.comicsinfo.R
import com.siderfighter.comicsinfo.presentation.viewmodel.RajComicsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: RajComicsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getRajComicsPage1()

        viewModel.rajComicsPage1.observe(this) {
            findViewById<TextView>(R.id.tv_hello).text = it.data.toString()
        }
    }
}