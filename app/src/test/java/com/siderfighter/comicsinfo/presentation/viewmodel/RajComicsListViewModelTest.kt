package com.siderfighter.comicsinfo.presentation.viewmodel

import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListItemModel
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListModel
import com.siderfighter.comicsinfo.domain.rajcomics.usecase.GetAllRajComicsUseCase
import com.siderfighter.comicsinfo.domain.rajcomics.usecase.GetRajComicsByPageUseCase
import com.siderfighter.comicsinfo.domain.rajcomics.usecase.SearchRajComicsListUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RajComicsListViewModelTest {

    private lateinit var viewModel: RajComicsListViewModel
    private lateinit var getAllRajComicsUseCase: GetAllRajComicsUseCase
    private lateinit var searchRajComicsListUseCase: SearchRajComicsListUseCase
    private lateinit var getRajComicsByPageUseCase: GetRajComicsByPageUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getAllRajComicsUseCase = mockk()
        searchRajComicsListUseCase = mockk()
        getRajComicsByPageUseCase = mockk()
        viewModel = RajComicsListViewModel(
            getAllRajComicsUseCase = getAllRajComicsUseCase,
            getRajComicsByPageUseCase = getRajComicsByPageUseCase,
            searchRajComicsListUseCase = searchRajComicsListUseCase
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }


    @Test
    fun getAllRajComics() = runTest {
        val rajComicsList = RajComicsListModel(
            listOf(
                RajComicsListItemModel(
                    comicName = "Curfew",
                    comicNumber = "1",
                    characterName = "Doga",
                )
            )
        )

        coEvery {
            getAllRajComicsUseCase.invokeUseCase()
        } returns flowOf(rajComicsList)

        viewModel.getAllRajComics()

        coVerify {
            getAllRajComicsUseCase.invokeUseCase()
        }

        runBlocking { // adding this block to make this test on function pass. Test on the class passes with or without this block
            delay(1)
        }
        val stateFlowValue = viewModel.allRajComicsList.value
        assertTrue(stateFlowValue == rajComicsList)
    }

    @Test
    fun getAllRajComicsErrorTest() = runTest {
        val rajComicsListModel = RajComicsListModel(emptyList())

        coEvery {
            getAllRajComicsUseCase.invokeUseCase()
        } returns flow {
            throw Exception("hello exception")
        }
        viewModel.getAllRajComics()

        val stateFlowValue = viewModel.allRajComicsList.drop(1).first()
        assertEquals(rajComicsListModel, stateFlowValue)
    }
}