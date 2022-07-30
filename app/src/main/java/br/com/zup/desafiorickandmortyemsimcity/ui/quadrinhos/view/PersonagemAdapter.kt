package br.com.zup.desafiorickandmortyemsimcity.ui.quadrinhos.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.desafiorickandmortyemsimcity.data.model.PersonagensResult
import br.com.zup.desafiorickandmortyemsimcity.databinding.PersonagensItemBinding
import br.com.zup.desafiorickandmortyemsimcity.ui.JPEG
import br.com.zup.desafiorickandmortyemsimcity.ui.URL_BASE_IMG
import com.squareup.picasso.Picasso

class PersonagemAdapter(
    private var personagemList: MutableList<PersonagensResult>,
    private val clickPersonagem: (personagem: PersonagensResult) -> Unit,
): RecyclerView.Adapter<PersonagemAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PersonagensItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val personagem = personagemList[position]
        holder.showPersonagem(personagem)
        holder.binding.cvItemListaPersonagens.setOnClickListener{
            clickPersonagem(personagem)
        }
    }

    override fun getItemCount(): Int = personagemList.size

    fun updatePersonagemList(atualizaLista: MutableList<PersonagensResult>){
        personagemList = atualizaLista
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: PersonagensItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun showPersonagem(personagemResult: PersonagensResult){
            Picasso.get().load(URL_BASE_IMG + personagemResult.id + JPEG)
                .into(binding.ivRick)
            binding.nomePersonagens.text = personagemResult.name
        }
    }
}