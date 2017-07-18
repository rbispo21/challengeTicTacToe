package br.com.gazeus.jogodavelha.game

import android.widget.ImageButton

interface GameContract {
    interface View {
        fun showMessageInvalidMove()
        fun getListBtn(): ArrayList<ImageButton>
        fun messageEndGame(message: String)
        fun enableNewGame()
        fun disableNewGame()
        fun clearButton()
    }

    interface Presenter {
        fun clickBtn(v: android.view.View)
        fun computerMove()
        fun hasWinner(plays: List<Int>): Boolean
        fun isDraw(): Boolean
        fun nextPlayer()
        fun playPerson(imgbtn: ImageButton)
        fun playComputer(imgbtn: ImageButton)
        fun endGameWithWinner()
        fun endGameDraw()
        fun reset()
    }
}