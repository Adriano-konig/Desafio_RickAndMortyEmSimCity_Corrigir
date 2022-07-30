package br.com.zup.desafiorickandmortyemsimcity.ui.detalhes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import br.com.zup.desafiorickandmortyemsimcity.R
import br.com.zup.desafiorickandmortyemsimcity.data.model.PersonagensResult
import br.com.zup.desafiorickandmortyemsimcity.databinding.FragmentDetalhesBinding
import br.com.zup.desafiorickandmortyemsimcity.ui.JPEG
import br.com.zup.desafiorickandmortyemsimcity.ui.PERSONAGEM_KEY
import br.com.zup.desafiorickandmortyemsimcity.ui.URL_BASE_IMG
import br.com.zup.desafiorickandmortyemsimcity.ui.home.view.HomeActivity
import com.squareup.picasso.Picasso


class DetalhesFragment : Fragment() {

    private lateinit var binding: FragmentDetalhesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetalhesBinding.inflate(inflater, container,false)
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
    }

    private fun getData(){
        val personagem = arguments?.getParcelable<PersonagensResult>(PERSONAGEM_KEY)
        if (personagem != null){
            favorito(personagem)
        }

        personagem?.let {
            Picasso.get().load(URL_BASE_IMG + it.id + JPEG).into(binding.ivDetalhe)
            binding.tvNameFieldPersonagem.text = it.name
            binding.tvStatusFieldPersonagem.text = it.status
            binding.tvSpeciesFieldPersonagem.text = it.species
            binding.tvGenderFieldPersonagem.text = it.gender
            (activity as HomeActivity).supportActionBar?.title = it.name

//            binding.ivFavorite.setImageDrawable(
//                ContextCompat.getDrawable(
//                    binding.root.context,
//                    if(personagem.isFavorite)
//                        R.drawable.ic_star_yellow
//                else{
//                    R.drawable.ic_star_off
//                    }
//                )
//            )
        }
    }

    private fun favorito(personagensResult: PersonagensResult){
        binding.ivFavorite.setOnClickListener {
            personagensResult.isFavorite = !personagensResult.isFavorite

            val bundle = bundleOf("ATUALIZADO" to personagensResult)
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_detalhesFragment_to_quadrinhosPersonagensRickAndMortyFragment)
        }
    }

}