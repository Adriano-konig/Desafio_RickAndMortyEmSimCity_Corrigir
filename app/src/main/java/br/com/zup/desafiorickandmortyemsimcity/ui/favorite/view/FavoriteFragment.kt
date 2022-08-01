package br.com.zup.desafiorickandmortyemsimcity.ui.favorite.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import br.com.zup.desafiorickandmortyemsimcity.R
import br.com.zup.desafiorickandmortyemsimcity.data.model.PersonagensResult
import br.com.zup.desafiorickandmortyemsimcity.databinding.FragmentFavoriteBinding
import br.com.zup.desafiorickandmortyemsimcity.ui.PERSONAGEM_KEY
import br.com.zup.desafiorickandmortyemsimcity.ui.favorite.viewmodel.FavoriteViewModel
import br.com.zup.desafiorickandmortyemsimcity.ui.viewstate.ViewState

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding

    private val favoriteViewModel: FavoriteViewModel by lazy {
        ViewModelProvider(this)[FavoriteViewModel::class.java]
    }

    private val adapter: FavoritePersonagemAdapter by lazy {
        FavoritePersonagemAdapter(mutableListOf(),this::irParaDetalhe)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentFavoriteBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        initObserver()
        favoriteViewModel.getAllMoviesFavorited()
        exibirRecyclerView()
    }

    private fun initObserver(){
        favoriteViewModel.personagemListFavoriteState.observe(this){
            when(it){
                is ViewState.Success ->{
                    Log.i("List", "${it.data}")
                    adapter.updateMovieList(it.data.toMutableList())
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        context,
                        "${it.throwable.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is ViewState.EmptyList -> {
                    adapter.updateMovieList(it.data.toMutableList())
                }
                else ->{}
            }
        }
    }

    private fun exibirRecyclerView(){
        binding.rvPersonagens.adapter = adapter
        binding.rvPersonagens.layoutManager = GridLayoutManager(context, 2)
    }

    private fun irParaDetalhe(personagens: PersonagensResult){
        val bundle = bundleOf(PERSONAGEM_KEY to personagens)
        NavHostFragment.findNavController(this).navigate(
            R.id.action_favoriteFragment_to_detalhesFragment,bundle
        )
    }
}