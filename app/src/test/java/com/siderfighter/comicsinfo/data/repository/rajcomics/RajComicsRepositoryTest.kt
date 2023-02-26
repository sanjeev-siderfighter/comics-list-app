package com.siderfighter.comicsinfo.data.repository.rajcomics

import com.siderfighter.comicsinfo.data.endpoints.RajComicsNetworkInterface
import com.siderfighter.comicsinfo.domain.rajcomics.IRajComicsRepository
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking

import org.junit.After
import org.junit.Before
import org.junit.Test

class RajComicsRepositoryTest {

    @RelaxedMockK
    private lateinit var networkInterface: RajComicsNetworkInterface
    private lateinit var repository: IRajComicsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = RajComicsRepository(networkInterface = networkInterface)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `test getting all raj comics`() {
        val sampleRajComicsResponse = RajComicsResponse(
            data = listOf(listOf()),
            nextPage = 0
        )
        coEvery { networkInterface.getAllRajComics() } returns sampleRajComicsResponse

        runBlocking {
            repository.getAllRajComics()
        }
        coVerify { networkInterface.getAllRajComics() }
    }

    @Test
    fun `test getting raj comics by page`() {
        val sampleRajComicsResponse = RajComicsResponse(
            data = listOf(listOf()),
            nextPage = 2
        )
        val samplePage = 1
        val sampleRequest = RajComicsPageRequest(
            page = samplePage
        )

        coEvery { networkInterface.getRajComicsByPage(sampleRequest) } returns sampleRajComicsResponse

        runBlocking {
            repository.getRajComicsByPage(samplePage)
        }

        coVerify { networkInterface.getRajComicsByPage(sampleRequest) }
    }
}