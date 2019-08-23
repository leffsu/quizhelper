package su.leff.ivanovoti9contest

import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val soundPool = SoundPool(5, AudioManager.STREAM_MUSIC, 0)
    private var victorySound = 0
    private var state = AppState.WAITING
    private val player1ActiveDrawable = R.drawable.ic_player1_active
    private val player1InactiveDrawable = R.drawable.ic_player1_inactive
    private val player2ActiveDrawable = R.drawable.ic_player2_active
    private val player2InactiveDrawable = R.drawable.ic_player2_inactive

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgv_player1_btn.setImageResource(player1InactiveDrawable)
        imgv_player2_btn.setImageResource(player2InactiveDrawable)
        victorySound = soundPool.load(this, R.raw.victory, 1)

        imgv_player1_btn.setOnClickListener {
            playerClickButton(Player.PLAYER1)
        }

        imgv_player2_btn.setOnClickListener {
            playerClickButton(Player.PLAYER2)
        }

        imgv_reset_btn.setOnClickListener {
            resetGame()
        }
    }

    private fun playerClickButton(player: Player) {

        if (state == AppState.WAITING) {

            try {
                soundPool.play(victorySound, 1f, 1f, 0, 0, 1f)
            } catch(exception: Exception){
                // I simply don't trust you
            }

            when (player) {
                Player.PLAYER1 -> {
                    state = AppState.PLAYER1
                    imgv_player1_btn.setImageResource(player1ActiveDrawable)
                }
                Player.PLAYER2 -> {
                    state = AppState.PLAYER2
                    imgv_player2_btn.setImageResource(player2ActiveDrawable)
                }
            }
        }
    }

    private fun resetGame() {
        state = AppState.WAITING
        resetIcons()
    }

    private fun resetIcons(){
        imgv_player1_btn.setImageResource(player1InactiveDrawable)
        imgv_player2_btn.setImageResource(player2InactiveDrawable)
    }

    enum class AppState {
        WAITING, PLAYER1, PLAYER2
    }

    enum class Player {
        PLAYER1, PLAYER2
    }
}
