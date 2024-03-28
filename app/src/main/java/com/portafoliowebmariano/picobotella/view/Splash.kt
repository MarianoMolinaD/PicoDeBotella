package com.portafoliowebmariano.picobotella.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.viewModels
import com.portafoliowebmariano.picobotella.R
import com.portafoliowebmariano.picobotella.viewmodel.PlayViewModel

class Splash : AppCompatActivity() {
    private val playViewModel : PlayViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
// Asegúrate de estar utilizando la versión 30 o superior de la API
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            val controller = window.insetsController
            if (controller != null) {
                // Ocultar barra de estado y barra de navegación de manera inmersiva
                controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                // Configurar la visibilidad de la barra de navegación en modo inmersivo
                controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            // Código de compatibilidad para versiones anteriores a Android 11
            @Suppress("DEPRECATION")
            window.decorView.apply {
                systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            }
        }


        playViewModel.splashScreen(this)

    }
}