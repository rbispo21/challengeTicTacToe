package br.com.gazeus.jogodavelha

import android.content.Context
import android.widget.ImageButton
import br.com.gazeus.jogodavelha.game.GameContract
import br.com.gazeus.jogodavelha.game.GamePresenter
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GamePresenterTest {

    @Mock
    lateinit var view: GameContract.View
    @Mock
    lateinit var context: Context

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun hasWinnerWithWinnerTest() {
        val presenter = GamePresenter(view, context)
        val list = arrayListOf<Int>()
        list.add(1)
        list.add(2)
        list.add(3)

        Assert.assertEquals(presenter.hasWinner(list), true)
    }

    @Test
    fun hasWinnerWitLooseTest() {
        val presenter = GamePresenter(view, context)
        val list = arrayListOf<Int>()
        list.add(1)
        list.add(2)
        list.add(5)

        Assert.assertEquals(presenter.hasWinner(list), false)
    }

    @Test
    fun isDrawWithDrawTest() {
        val presenter = GamePresenter(view, context)
        Mockito.`when`(presenter.listAvailablePlays).thenReturn(arrayListOf())

        Assert.assertEquals(presenter.isDraw(), true)
    }

    @Test
    fun isDrawWithoutDrawTest() {
        val presenter = GamePresenter(view, context)
        val list = arrayListOf<ImageButton>()
        list.add(ImageButton(context))
        list.add(ImageButton(context))
        presenter.listAvailablePlays = list

        Assert.assertEquals(presenter.isDraw(), false)
    }

}