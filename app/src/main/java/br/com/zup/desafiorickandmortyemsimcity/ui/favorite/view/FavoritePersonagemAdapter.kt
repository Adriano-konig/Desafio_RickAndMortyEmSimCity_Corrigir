package br.com.zup.desafiorickandmortyemsimcity.ui.favorite.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.desafiorickandmortyemsimcity.R
import br.com.zup.desafiorickandmortyemsimcity.data.model.PersonagensResult
import br.com.zup.desafiorickandmortyemsimcity.databinding.PersonagensItemBinding
import br.com.zup.desafiorickandmortyemsimcity.ui.JPEG
import br.com.zup.desafiorickandmortyemsimcity.ui.URL_BASE_IMG
import com.squareup.picasso.Picasso

class FavoritePersonagemAdapter(
    private var personagemList: MutableList<PersonagensResult>,
    private val clickPersonagem: (movieResult: PersonagensResult) -> Unit
) :
    RecyclerView.Adapter<FavoritePersonagemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PersonagensItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val personagem = personagemList[position]
        holder.showMovieInfo(personagem)
        holder.binding.cvItemListaPersonagens.setOnClickListener {
            clickPersonagem(personagem)
            updateMovieList(personagemList)
        }
//        holder.binding..setOnClickListener {
//            personagem.isFavorite = !personagem.isFavorite
//            clickDisfavor(personagem)
//            personagemList.remove(personagem)
//            notifyItemRemoved(position)
//        }
    }

    override fun getItemCount() = personagemList.size

    fun updateMovieList(newList: MutableList<PersonagensResult>) {
        personagemList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: PersonagensItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun showMovieInfo(personagensResult: PersonagensResult) {
            Picasso.get().load(URL_BASE_IMG + personagensResult.id + JPEG )
                .into(binding.ivRick)
            binding.nomePersonagens.text = personagensResult.name
//            binding.ivFavorite.setImageDrawable(
//                ContextCompat.getDrawable(
//                    binding.root.context,
//                    if (movieResult.isFavorite)
//                        R.drawable.ic_favorite
//                    else
//                        R.drawable.ic_disfavor
//                )
//            )
        }
    }
}
