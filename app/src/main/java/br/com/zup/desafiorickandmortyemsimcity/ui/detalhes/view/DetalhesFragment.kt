package br.com.zup.desafiorickandmortyemsimcity.ui.detalhes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import br.com.zup.desafiorickandmortyemsimcity.R
import br.com.zup.desafiorickandmortyemsimcity.data.model.PersonagensResult
import br.com.zup.desafiorickandmortyemsimcity.databinding.FragmentDetalhesBinding
import br.com.zup.desafiorickandmortyemsimcity.ui.DESFAVORITADO
import br.com.zup.desafiorickandmortyemsimcity.ui.FAVORITADO_SUCESSO
import br.com.zup.desafiorickandmortyemsimcity.ui.PERSONAGEM_KEY
import br.com.zup.desafiorickandmortyemsimcity.ui.favorite.viewmodel.FavoriteViewModel
import br.com.zup.desafiorickandmortyemsimcity.ui.home.view.HomeActivity
import com.squareup.picasso.Picasso


class DetalhesFragment : Fragment() {

    private lateinit var binding: FragmentDetalhesBinding

    private val favoriteViewModel: FavoriteViewModel by lazy {
        ViewModelProvider(this)[FavoriteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetalhesBinding.inflate(inflater, container,false)
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val personagem = pegarPersonagens()
        personagem?.let {
            clickfavorito(it)
            exibirData(it)
        }
        initObserve(personagem)
    }

    private fun clickfavorito(personagens: PersonagensResult){
        binding.ivFavorite.setOnClickListener {
            personagens.isFavorite = !personagens.isFavorite
            updateFavorito(personagens)
            exibirData(personagens)

            if (personagens.isFavorite){
                Toast.makeText(
                    context,
                    FAVORITADO_SUCESSO,
                    Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(
                    context,
                    DESFAVORITADO,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun initObserve(personagens: PersonagensResult?){
        favoriteViewModel.personagemListFavoriteState.observe(this){
            personagens?.let {
                personagensStatus(it)
            }
        }
    }

    private fun personagensStatus(personagens: PersonagensResult){
        binding.ivFavorite.setImageDrawable(
            ContextCompat.getDrawable(
                binding.root.context,
                if(personagens.isFavorite) {
                    R.drawable.ic_star_yellow
                } else {
                    R.drawable.ic_star_off
                }
            )
        )
    }

    private fun updateFavorito(personagens: PersonagensResult){
        favoriteViewModel.disfavorPersonagens(personagens)
    }


    private fun exibirData(personagens: PersonagensResult){
        personagens.apply {
            Picasso.get().load(image ).into(binding.ivDetalhe)
            binding.tvNameFieldPersonagem.text = name
            binding.tvStatusFieldPersonagem.text = status
            binding.tvSpeciesFieldPersonagem.text = species
            binding.tvGenderFieldPersonagem.text = gender
            personagensStatus(personagens)
            (activity as HomeActivity).supportActionBar?.title = name
        }
    }

    private fun pegarPersonagens(): PersonagensResult?{
        return arguments?.getParcelable(PERSONAGEM_KEY)
    }
}