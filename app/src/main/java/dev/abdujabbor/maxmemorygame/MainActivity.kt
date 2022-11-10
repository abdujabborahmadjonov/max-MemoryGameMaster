package dev.abdujabbor.maxmemorygame

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mediaPlayer =MediaPlayer.create(this,R.raw.background)
        mediaPlayer.start()

        btn_easy.setOnClickListener{
            mediaPlayer.pause()
            val intent = Intent(this,EasyGame::class.java)
            startActivity(intent)
        }

        btn_middle.setOnClickListener{
            mediaPlayer.pause()
            val intent = Intent(this,MiddleGame::class.java)
            startActivity(intent)
        }
        btn_hard.setOnClickListener{
            mediaPlayer.pause()
            val intent = Intent(this,HardGame::class.java)
            startActivity(intent)
        }
    }


}