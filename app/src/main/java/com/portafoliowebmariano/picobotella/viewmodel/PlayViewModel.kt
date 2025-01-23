package com.portafoliowebmariano.picobotella.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.portafoliowebmariano.picobotella.R
import com.portafoliowebmariano.picobotella.model.Challenge
import com.portafoliowebmariano.picobotella.model.Pokemon
import com.portafoliowebmariano.picobotella.repository.ChallengeRepository
import com.portafoliowebmariano.picobotella.utils.constants
import com.portafoliowebmariano.picobotella.utils.constants.TIME
import com.portafoliowebmariano.picobotella.view.MainActivity
import com.portafoliowebmariano.picobotella.view.dialog.DialogShowChallenge.showDialogChallenge
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random

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
    val listChallenge: LiveData<MutableList<Challenge>> get() = _listChallenge

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _listPokemon = MutableLiveData<MutableList<Pokemon>>()
    val listPokemon : LiveData<MutableList<Pokemon>> get() = _listPokemon

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

    @RequiresApi(Build.VERSION_CODES.M)
    fun dialogShowChallenge(
        context: Context,
        audioBackground: MediaPlayer,
        isMute: Boolean,
        messageChallenge: String,
        playViewModel: PlayViewModel
    ) {
        showDialogChallenge(context, audioBackground, isMute, messageChallenge, playViewModel)
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
            _isLoading.value = true
            try {
                challengeRepository.addChallenge(challenge)
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }

    fun getListChallenge() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _listChallenge.value = challengeRepository.getListChallenge()
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }

    fun deleteChallenge(challenge: Challenge) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                challengeRepository.deleteChallenge(challenge)
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }

    fun updateChallenge(challenge: Challenge) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                challengeRepository.updateChallenge(challenge)
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }

    fun getChallenge(listChallenge: MutableList<Challenge>): String {
        var description = ""

        return if (listChallenge.isNotEmpty()) {
            val size = listChallenge.size
            val randomChallenge = Random.nextInt(0, size)
            description = listChallenge[randomChallenge].DescriptionChallenge
            description
        } else {
            val emptyChallenge = constants.EMPTYCHLLENGE
            emptyChallenge
        }
    }

    fun getUrlApp(activity: Activity): String {
//        val namePackage = activity.packageName
        val namePackage = "com.portafoliowebmariano.factschucknorris&hl=es&gl=US"
        val urlApp = "https://play.google.com/store/apps/dev?id=5820982989716989300"
        return urlApp
    }

    fun shareApp(audioBackground: MediaPlayer, activity: Activity) {
        audioBackground.pause()
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Pico Botella")
//        val nombrePaquete = activity.packageName
        val namePackage = "com.portafoliowebmariano.factschucknorris&hl=es&gl=US"
        val eslogan = "App pico de botella \nSolo los valientes juegan!!"
        val urlApp = "https://play.google.com/store/apps/details?id=${namePackage}"
        var share = eslogan + urlApp
        intent.putExtra(Intent.EXTRA_TEXT, share)
        activity.startActivity(intent)
    }
    fun listPokemon(){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _listPokemon.value = challengeRepository.getListPokemon()
                _isLoading.value = false
            }catch (e:Exception){
                _isLoading.value = false
            }
        }
    }

    fun obtenerPokemon(listPokemon: MutableList<Pokemon>): Pokemon? {
        var pokemon : Pokemon? = null
        return if (listPokemon.isNotEmpty()){
            val size = listPokemon.size
            val randomPokemon = Random.nextInt(0,size)
            pokemon = listPokemon[randomPokemon]
            pokemon
        }else
        {
            pokemon
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }
}