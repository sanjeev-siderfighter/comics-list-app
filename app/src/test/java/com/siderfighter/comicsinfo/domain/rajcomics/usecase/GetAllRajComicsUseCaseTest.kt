package com.siderfighter.comicsinfo.domain.rajcomics.usecase

import com.siderfighter.comicsinfo.data.repository.rajcomics.RajComicsResponse
import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsRepository
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListItemModel
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListModel
import io.mockk.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetAllRajComicsUseCaseTest {

    private val rajComicsRepository: IRajComicsRepository = mockk()
    private lateinit var getAllRajComicsUseCase: GetAllRajComicsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init()
        getAllRajComicsUseCase = GetAllRajComicsUseCase(rajComicsRepository = rajComicsRepository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `test get all raj comics usecase without need for trimming`() {
        val sampleResponse = RajComicsResponse(
            data = listOf(
                listOf(
                    "1", "Curfew", "34", "Doga"
                )
            ),
            nextPage = 0
        )

        val expectedRajComicsModel = RajComicsListModel(
            rajComicsList = listOf(
                RajComicsListItemModel(
                    comicName = "Curfew",
                    comicNumber = "34",
                    characterName = "Doga"
                )
            )
        )

        coEvery { rajComicsRepository.getAllRajComics() } returns sampleResponse

        runBlocking {
            val rajComicsModel = getAllRajComicsUseCase.invokeUseCase().first()
            assertEquals(expectedRajComicsModel, rajComicsModel)
        }

        coVerify { rajComicsRepository.getAllRajComics() }
    }

    @Test
    fun `test get all raj comics usecase with need for trimming`() {
        val sampleResponse = RajComicsResponse(
            data = listOf(
                listOf(
                    "   1   ", "    Curfew", "34    ", "    Doga        "
                )
            ),
            nextPage = 0
        )

        val expectedRajComicsModel = RajComicsListModel(
            rajComicsList = listOf(
                RajComicsListItemModel(
                    comicName = "Curfew",
                    comicNumber = "34",
                    characterName = "Doga"
                )
            )
        )

        coEvery { rajComicsRepository.getAllRajComics() } returns sampleResponse

        runBlocking {
            val rajComicsModel = getAllRajComicsUseCase.invokeUseCase().first()
            assertEquals(expectedRajComicsModel, rajComicsModel)
        }

        coVerify { rajComicsRepository.getAllRajComics() }
    }
}