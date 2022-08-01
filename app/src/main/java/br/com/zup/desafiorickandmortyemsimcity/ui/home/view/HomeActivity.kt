package br.com.zup.desafiorickandmortyemsimcity.ui.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import br.com.zup.desafiorickandmortyemsimcity.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(){
    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionbar()
        iniciarNavegacao()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun supportActionbar(){
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

   private fun iniciarNavegacao(){
       val navHostFragment =
           supportFragmentManager.findFragmentById(binding.container.id) as NavHostFragment
       val navController = navHostFragment.navController
       NavigationUI.setupActionBarWithNavController(this, navController)
    }




}