package br.com.zup.desafiorickandmortyemsimcity.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class SplashActivityViewModel : ViewModel() {

    private val _goToHome = MutableLiveData<Boolean>()
    val goToHome : LiveData<Boolean> get() = _goToHome

    fun goToHome(){
        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(3))
            withContext(Dispatchers.Main) {
               _goToHome.value = true
            }
        }
    }
}