package com.siderfighter.comicsinfo.domain.rajcomics.usecase

import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListItemModel
import com.siderfighter.comicsinfo.domain.rajcomics.RajComicsListModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SearchRajComicsListUseCaseTest {

    @MockK
    private val searchRajComicsListUseCase: SearchRajComicsListUseCase =
        spyk(recordPrivateCalls = true)

    @Before
    fun setUp() {
        MockKAnnotations.init()
        /*sampleRajComicsListModel = RajComicsListModel(
            rajComicsList = listOf(
                RajComicsListItemModel(
                    comicName = "Curfew",
                    characterName = "Doga",
                    comicNumber = "34"
                ),
                RajComicsListItemModel(
                    comicNumber = "23",
                    comicName = "All Star Superman",
                    characterName = "Superman"
                ),
                RajComicsListItemModel(
                    comicName = "Blackest Night",
                    comicNumber = "53",
                    characterName = "Green Lantern"
                )
            )
        )*/
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test search raj comics by character case key character is not present`() = runTest {
        val sampleRajComicsListModel = RajComicsListModel(
            rajComicsList = listOf(
                RajComicsListItemModel(
                    comicName = "Curfew",
                    characterName = "Doga",
                    comicNumber = "34"
                ),
                RajComicsListItemModel(
                    comicNumber = "23",
                    comicName = "All Star Superman",
                    characterName = "Superman"
                ),
                RajComicsListItemModel(
                    comicName = "Blackest Night",
                    comicNumber = "53",
                    characterName = "Green Lantern"
                ),
                RajComicsListItemModel(
                    comicNumber = "33",
                    comicName = "Superman Birthright",
                    characterName = "Superman"
                ),
            )
        )

        val sampleKey = "Doga"

        val actual = searchRajComicsListUseCase.invokeUseCase(
            rajComicsList = sampleRajComicsListModel,
            key = sampleKey
        )

        assertEquals(1, actual.first().rajComicsList.size)
    }

    @Test
    fun `test search raj comics by character case key character is present`() {

    }

    @Test
    fun `test search raj comics by comic case key comic is not present`() {

    }

    @Test
    fun `test search raj comics by comic case key comic is present`() {

    }

    @Test
    fun `test search raj comics by comic number case key comic number is not present`() {

    }

    @Test
    fun `test search raj comics by comic number case key comic number is present`() {

    }

    @Test
    fun `test search raj comics when key contains all of comic name, comic number and character name`() {

    }
}