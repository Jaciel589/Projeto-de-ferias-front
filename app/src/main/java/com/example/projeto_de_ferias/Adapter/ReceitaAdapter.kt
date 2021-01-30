package com.example.projeto_de_ferias.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.projeto_de_ferias.Activitys.EditarReceita
import com.example.projeto_de_ferias.Activitys.ExcluirReceita
import com.example.projeto_de_ferias.Models.Receita
import com.example.projeto_de_ferias.R
import kotlinx.android.synthetic.main.adapter_receita.view.*

class ReceitaAdapter (private val receitas: ArrayList<Receita>) : RecyclerView.Adapter<ReceitaAdapter.ViewHolder>(){


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemButtonEditarReceita: Button = itemView.findViewById(R.id.buttonEditarReceita)
        val itemButtonExcluirReceita: Button = itemView.findViewById(R.id.buttonExcluirReceita)

        fun pegarDados(receita: Receita) {
            itemView.titulo.text = receita.titulo
            itemView.descricao.text = receita.descricao
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_receita, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = receitas[position]
        holder.pegarDados(item)

        val context = holder.itemButtonEditarReceita.context
        val contextButtonExcluirReceita = holder.itemButtonExcluirReceita.context

        holder.itemButtonExcluirReceita.setOnClickListener {
                v: View -> Unit

            val intent = Intent(contextButtonExcluirReceita, ExcluirReceita::class.java).apply {
                putExtra("id",item.id)
                putExtra("descricao",item.descricao)
                putExtra("titulo",item.titulo)
            }
            context.startActivity(intent)
        }

        holder.itemButtonEditarReceita.setOnClickListener {
                v: View -> Unit

            val intent = Intent(context, EditarReceita::class.java).apply {
                putExtra("id",item.id)
                putExtra("descricao",item.descricao)
                putExtra("titulo",item.titulo)
            }
            context.startActivity(intent)
        }

    }
    override fun getItemCount(): Int {
        return receitas.size
    }


}
