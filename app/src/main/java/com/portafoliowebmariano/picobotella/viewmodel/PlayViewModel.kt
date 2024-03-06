package com.portafoliowebmariano.picobotella.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
import android.provider.MediaStore.Audio.Media
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.portafoliowebmariano.picobotella.utils.constants.TIME
import com.portafoliowebmariano.picobotella.view.MainActivity
import com.portafoliowebmariano.picobotella.view.dialog.DialogShowChallenge.showDialogChallenge
import kotlinx.coroutines.delay

class PlayViewModel : ViewModel() {

    private val _rotationBotle = MutableLiveData<RotateAnimation>()
    val rotationBottle: LiveData<RotateAnimation> get() = _rotationBotle

    private val _enableButton = MutableLiveData(true)
    val enableButton : LiveData<Boolean> get() = _enableButton

    private val _enabledStreamers = MutableLiveData(false)
    val enableStreamers : LiveData<Boolean> get() = _enabledStreamers

    private val _statusShowDialog = MutableLiveData(false)
    val statusShowDialog: LiveData<Boolean> get() = _statusShowDialog

    private val _statusRotationBottle = MutableLiveData(false)
    val statusRotationBottle : LiveData<Boolean> get() = _statusRotationBottle

    private val _isMute = MutableLiveData(false)
    val isMute : LiveData<Boolean> get() = _isMute

    fun splashScreen(activity: Activity) {
        val handler = Handler()
        handler.postDelayed({
            activity.startActivity(Intent(activity, MainActivity::class.java))
            activity.finish()
        }, TIME)
    }



    fun spinBottle() {
        _statusRotationBottle.value = true

        val degrees = (Math.random() * 3600) + 1080
        val rotation = RotateAnimation(
            0f, degrees.toFloat(),
            Animation.RELATIVE_TO_SELF,
            0.5f, Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotation.fillAfter = true
        rotation.duration = 3600
        rotation.interpolator = DecelerateInterpolator()

        rotation.setAnimationListener(object  : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                _enabledStreamers.value = true
                _enableButton.value = false
            }

            override fun onAnimationEnd(p0: Animation?) {
                _statusRotationBottle.value = false
                _enableButton.value = true
                _enabledStreamers.value = false
                _statusShowDialog.value = true
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })

        _rotationBotle.value = rotation
    }

    fun dialogShowChallenge(context: Context,audioBackground : MediaPlayer,isMute: Boolean, messageChallenge: String) {
        showDialogChallenge(context,audioBackground,isMute,messageChallenge)
    }

    fun statusShowDialog(status: Boolean) {
        _statusShowDialog.value = status
    }

    suspend fun wait(time : Int){
        delay(time * 1000L)
    }
    fun modifySound(modify : Boolean){
        _isMute.value = modify
    }
}