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

        val tv = findViewById<TextView>(R.id.tv_hello)

//        viewModel.getAllRajComics()
        viewModel.getRajComicsByPage(2)

        viewModel.rajComicsPage1.observe(this) {
//            tv.text = it.data.toString()
        }

        viewModel.rajComicsByPage.observe(this) {
            tv.text = tv.text.toString() + it.data.toString()
        }
    }
}