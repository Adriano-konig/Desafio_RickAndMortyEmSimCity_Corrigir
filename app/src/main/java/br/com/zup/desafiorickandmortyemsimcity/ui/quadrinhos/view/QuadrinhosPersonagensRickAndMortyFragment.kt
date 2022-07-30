package br.com.zup.desafiorickandmortyemsimcity.ui.quadrinhos.view

import android.os.Bundle
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
import br.com.zup.desafiorickandmortyemsimcity.databinding.FragmentQuadrinhosPersonagensRickAndMortyBinding
import br.com.zup.desafiorickandmortyemsimcity.ui.PERSONAGEM_KEY
import br.com.zup.desafiorickandmortyemsimcity.ui.quadrinhos.viewmodel.QuadrinhosPersonagensRickAndMortyViewModel
import br.com.zup.desafiorickandmortyemsimcity.ui.viewstate.ViewState

class QuadrinhosPersonagensRickAndMortyFragment : Fragment() {

    private lateinit var binding: FragmentQuadrinhosPersonagensRickAndMortyBinding

    private val viewmodel: QuadrinhosPersonagensRickAndMortyViewModel by lazy {
        ViewModelProvider(this)[QuadrinhosPersonagensRickAndMortyViewModel::class.java]
    }

    private val adapter: PersonagemAdapter by lazy {
        PersonagemAdapter(arrayListOf(), this::irParaDetalhes)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            FragmentQuadrinhosPersonagensRickAndMortyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRvPersonagens()
        favoriteDePersonagens()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel.getAllPersonagensNetwork()
    }

    private fun initObserver() {
        viewmodel.personagensList.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    adapter.updatePersonagemList(it.data.toMutableList())
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        context,
                        "${it.throwable.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is ViewState.EmptyList -> {
                    Toast.makeText(
                        context,
                        "Error",
                        Toast.LENGTH_LONG
                    ).show()
            }
            else -> {}
        }
    }
}

//        viewmodel.loading.observe(this.viewLifecycleOwner) {
//            when (it) {
//                is ViewState.Loading -> {
//                    binding.pbLoading.isVisible = it.loading == true
//                }
//                else -> {}
//            }
//        }
//    }

        private fun setUpRvPersonagens() {
            initObserver()
            binding.rvPersonagens.adapter = adapter
            binding.rvPersonagens.layoutManager = GridLayoutManager(context, 2)
        }



        private fun irParaDetalhes(personagensResult: PersonagensResult){
            val bundle = bundleOf(PERSONAGEM_KEY to personagensResult)

            NavHostFragment.findNavController(this).navigate(
                R.id.action_quadrinhosPersonagensRickAndMortyFragment_to_detalhesFragment,bundle
            )
        }

    private fun favoriteDePersonagens(){
        binding.floating.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                R.id.action_quadrinhosPersonagensRickAndMortyFragment_to_favoriteFragment
            )
        }

    }

}