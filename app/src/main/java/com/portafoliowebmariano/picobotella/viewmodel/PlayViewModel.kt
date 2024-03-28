package com.portafoliowebmariano.picobotella.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
import android.provider.MediaStore.Audio.Media
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portafoliowebmariano.picobotella.model.Challenge
import com.portafoliowebmariano.picobotella.repository.ChallengeRepository
import com.portafoliowebmariano.picobotella.utils.constants.TIME
import com.portafoliowebmariano.picobotella.view.MainActivity
import com.portafoliowebmariano.picobotella.view.dialog.DialogShowChallenge.showDialogChallenge
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class PlayViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>()
    private val challengeRepository = ChallengeRepository(context)

    private val _rotationBotle = MutableLiveData<RotateAnimation>()
    val rotationBottle: LiveData<RotateAnimation> get() = _rotationBotle

    private val _enableButton = MutableLiveData(true)
    val enableButton: LiveData<Boolean> get() = _enableButton

    private val _enabledStreamers = MutableLiveData(false)
    val enableStreamers: LiveData<Boolean> get() = _enabledStreamers

    private val _statusShowDialog = MutableLiveData(false)
    val statusShowDialog: LiveData<Boolean> get() = _statusShowDialog

    private val _statusRotationBottle = MutableLiveData(false)
    val statusRotationBottle: LiveData<Boolean> get() = _statusRotationBottle

    private val _isMute = MutableLiveData(false)
    val isMute: LiveData<Boolean> get() = _isMute

    private val _listChallenge = MutableLiveData<MutableList<Challenge>>()
    val listChallenge : LiveData<MutableList<Challenge>> get() = _listChallenge

    fun splashScreen(activity: Activity) {
       val executor = Executors.newSingleThreadScheduledExecutor()
        executor.schedule({
            activity.startActivity(Intent(activity, MainActivity::class.java))
            activity.finish()
        }, TIME, TimeUnit.MILLISECONDS)
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

        rotation.setAnimationListener(object : Animation.AnimationListener {
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

    fun dialogShowChallenge(
        context: Context,
        audioBackground: MediaPlayer,
        isMute: Boolean,
        messageChallenge: String,
    ) {
        showDialogChallenge(context, audioBackground, isMute, messageChallenge)
    }

    fun statusShowDialog(status: Boolean) {
        _statusShowDialog.value = status
    }

    suspend fun wait(time: Int) {
        delay(time * 1000L)
    }

    fun modifySound(modify: Boolean) {
        _isMute.value = modify
    }

    fun addChallenge(challenge: Challenge) {
        viewModelScope.launch {
            try {
                challengeRepository.addChallenge(challenge)
            } catch (e: Exception) {

            }
        }
    }

    fun getListChallenge(){
        viewModelScope.launch {
            try {
                _listChallenge.value = challengeRepository.getListChallenge()
            }catch (e : Exception){

            }
        }
    }
    fun deleteChallenge(challenge: Challenge){
        viewModelScope.launch {
            try {
                challengeRepository.deleteChallenge(challenge)
            }catch (e: Exception){

            }
        }
    }
    fun updateChallenge (challenge: Challenge){
        viewModelScope.launch {
            try {
                challengeRepository.updateChallenge(challenge)
            }catch (e : Exception){

            }
        }
    }
}