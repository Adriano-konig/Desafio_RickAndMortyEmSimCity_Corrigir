package br.com.zup.desafiorickandmortyemsimcity.ui.splash.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.desafiorickandmortyemsimcity.R
import br.com.zup.desafiorickandmortyemsimcity.ui.home.view.HomeActivity
import br.com.zup.desafiorickandmortyemsimcity.ui.splash.viewmodel.SplashActivityViewModel

class SplashActivity : AppCompatActivity() {

    private val viewModel by lazy { SplashActivityViewModel() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupObservable()
        shouldGoToHome()
    }

    private fun setupObservable(){
        viewModel.goToHome.observe(
            this,
            androidx.lifecycle.Observer {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        )
    }
    private fun shouldGoToHome() {
        viewModel.goToHome()
    }
}
