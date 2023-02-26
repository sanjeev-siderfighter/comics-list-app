package com.siderfighter.comicsinfo.domain.rajcomics.usecase

import com.siderfighter.comicsinfo.data.repository.rajcomics.RajComicsResponse
import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsRepository
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListItemModel
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetRajComicsByPageUseCaseTest {

    @MockK
    private val rajComicsRepository: IRajComicsRepository = mockk()
    private lateinit var getRajComicsByPageUseCase: GetRajComicsByPageUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init()
        getRajComicsByPageUseCase = GetRajComicsByPageUseCase(rajComicsRepository = rajComicsRepository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test get raj comics by page use case without need for trimming`() {
        val samplePage = 1
        val sampleResponse = RajComicsResponse(
            nextPage = 2,
            data = listOf(listOf(
                "1", "Curfew", "34", "Doga"
            ))
        )
        val expectedRajComicsListModel = RajComicsListModel(
            rajComicsList = listOf(
                RajComicsListItemModel(
                    comicNumber = "34",
                    comicName = "Curfew",
                    characterName = "Doga"
                )
            )
        )

        coEvery { rajComicsRepository.getRajComicsByPage(samplePage) } returns sampleResponse

        runTest {
            val rajComicsListModel = getRajComicsByPageUseCase.invokeUseCase(samplePage)
            Assert.assertEquals(expectedRajComicsListModel, rajComicsListModel)
        }

        coVerify { rajComicsRepository.getRajComicsByPage(samplePage) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test get raj comics by page use case with need for trimming`() {
        val samplePage = 1
        val sampleResponse = RajComicsResponse(
            nextPage = 2,
            data = listOf(listOf(
                "1", "   Curfew   ", "    34", "Doga    "
            ))
        )
        val expectedRajComicsListModel = RajComicsListModel(
            rajComicsList = listOf(
                RajComicsListItemModel(
                    comicNumber = "34",
                    comicName = "Curfew",
                    characterName = "Doga"
                )
            )
        )

        coEvery { rajComicsRepository.getRajComicsByPage(samplePage) } returns sampleResponse

        runTest {
            val rajComicsListModel = getRajComicsByPageUseCase.invokeUseCase(samplePage)
            Assert.assertEquals(expectedRajComicsListModel, rajComicsListModel)
        }

        coVerify { rajComicsRepository.getRajComicsByPage(samplePage) }
    }
}