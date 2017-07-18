package br.com.gazeus.jogodavelha.game

import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast

import br.com.gazeus.jogodavelha.R
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity(), GameContract.View {
    lateinit var presenter: GameContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        presenter = GamePresenter(this, this)
    }

    fun imgBtnClick(view: View) {
        presenter.clickBtn(view)
    }

    fun newGameBtnClick(view: View) {
        presenter.reset()
    }

    override fun getListBtn(): ArrayList<ImageButton> {
        val btns = arrayListOf<ImageButton>()
        btns.add(imgbtnl1c1)
        btns.add(imgbtnl1c2)
        btns.add(imgbtnl1c3)
        btns.add(imgbtnl2c1)
        btns.add(imgbtnl2c2)
        btns.add(imgbtnl2c3)
        btns.add(imgbtnl3c1)
        btns.add(imgbtnl3c2)
        btns.add(imgbtnl3c3)

        return btns
    }

    override fun showMessageInvalidMove() {
        Toast.makeText(this, getString(R.string.msg_invalid_move), Toast.LENGTH_SHORT).show()
    }

    override fun messageEndGame(message: String) {
        txtTitle.text = message
    }

    override fun enableNewGame() {
        btnNewGame.visibility = View.VISIBLE
    }

    override fun disableNewGame() {
        btnNewGame.visibility = View.GONE
    }

    override fun clearButton() {
        imgbtnl1c1.setImageResource(android.R.color.transparent)
        imgbtnl1c2.setImageResource(android.R.color.transparent)
        imgbtnl1c3.setImageResource(android.R.color.transparent)
        imgbtnl2c1.setImageResource(android.R.color.transparent)
        imgbtnl2c2.setImageResource(android.R.color.transparent)
        imgbtnl2c3.setImageResource(android.R.color.transparent)
        imgbtnl3c1.setImageResource(android.R.color.transparent)
        imgbtnl3c2.setImageResource(android.R.color.transparent)
        imgbtnl3c3.setImageResource(android.R.color.transparent)
    }

}
