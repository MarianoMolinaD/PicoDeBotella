package com.portafoliowebmariano.picobotella.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.portafoliowebmariano.picobotella.R
import com.portafoliowebmariano.picobotella.viewmodel.PlayViewModel

class Splash : AppCompatActivity() {
    private val playViewModel : PlayViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        playViewModel.splashScreen(this)

    }
}