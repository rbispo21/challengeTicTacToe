package br.com.gazeus.jogodavelha.game

import android.content.Context
import android.view.View
import android.widget.ImageButton
import br.com.gazeus.jogodavelha.R
import rx.Single
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class GamePresenter(val view: GameContract.View, val context: Context): GameContract.Presenter {
    private val PERSON = 1
    private val COMPUTER = 2
    var currentPlayer = PERSON
    var listAvailablePlays = view.getListBtn()
    private var playsPerson = arrayListOf<Int>()
    private var playsComputer = arrayListOf<Int>()
    var endGame = false

    override fun clickBtn(v: View) {
        val imgbtn = (v as ImageButton)
        if (!endGame && listAvailablePlays.contains(imgbtn)) {
            listAvailablePlays.remove(imgbtn)
            when(currentPlayer) {
                PERSON -> {
                    Single.just(playPerson(imgbtn))
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe()
                }
                COMPUTER -> {
                    Single.just(playComputer(imgbtn))
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe()
                }
            }
        }else {
            view.showMessageInvalidMove()
        }
    }

    override fun computerMove() {
        val random = Random()
        val randomNumber = random.nextInt(listAvailablePlays.size)
        val btn  = listAvailablePlays[randomNumber]
        btn.performClick()
    }


    override fun hasWinner(plays: List<Int>): Boolean {
        return (plays.contains(1) && plays.contains(2) && plays.contains(3)) ||
                (plays.contains(4) && plays.contains(5) && plays.contains(6)) ||
                (plays.contains(7) && plays.contains(8) && plays.contains(9)) ||
                (plays.contains(1) && plays.contains(4) && plays.contains(7)) ||
                (plays.contains(2) && plays.contains(5) && plays.contains(8)) ||
                (plays.contains(3) && plays.contains(6) && plays.contains(9)) ||
                (plays.contains(1) && plays.contains(5) && plays.contains(9)) ||
                (plays.contains(3) && plays.contains(5) && plays.contains(7))
    }

    override fun isDraw(): Boolean {
        return listAvailablePlays.size == 0
    }

    override fun reset() {
        endGame = false
        view.disableNewGame()
        view.messageEndGame(context.getString(R.string.app_name))
        view.clearButton()

        currentPlayer = PERSON
        listAvailablePlays = view.getListBtn()
        playsPerson = arrayListOf<Int>()
        playsComputer = arrayListOf<Int>()
    }

    override fun nextPlayer() {
        if (currentPlayer == PERSON)
            currentPlayer = COMPUTER
        else
            currentPlayer = PERSON
    }
    override fun playPerson(imgbtn: ImageButton) {
        imgbtn.setImageResource(R.drawable.cell_x)
        playsPerson.add((imgbtn.tag as String).toInt())

        if(hasWinner(playsPerson)) {
            endGameWithWinner()
        } else if (isDraw()) {
            endGameDraw()
        } else {
            nextPlayer()
            computerMove()
        }
    }

    override fun playComputer(imgbtn: ImageButton) {
        imgbtn.setImageResource(R.drawable.cell_o)
        playsComputer.add((imgbtn.tag as String).toInt())

        if(hasWinner(playsComputer)) {
            endGameWithWinner()
        } else if (isDraw()) {
            endGameDraw()
        } else {
            nextPlayer()
        }
    }
    override fun endGameWithWinner() {
        endGame = true
        if (currentPlayer == PERSON)
            view.messageEndGame(context.getString(R.string.msg_win))
        else
            view.messageEndGame(context.getString(R.string.msg_loose))
        view.enableNewGame()
    }

    override fun endGameDraw() {
        endGame = true
        view.messageEndGame(context.getString(R.string.msg_draw))
        view.enableNewGame()
    }
}