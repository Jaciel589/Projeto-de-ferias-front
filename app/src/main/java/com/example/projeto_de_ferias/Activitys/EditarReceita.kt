package com.example.projeto_de_ferias.Activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.projeto_de_ferias.Models.Receita
import com.example.projeto_de_ferias.R
import com.example.projeto_de_ferias.Retrofit.RetrofitClient
import com.example.projeto_de_ferias.Service.ReceitaService
import kotlinx.android.synthetic.main.activity_editar_receita.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditarReceita : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_receita)

        supportActionBar?.title = "Editar receita"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        val id = intent.getStringExtra("id").toString()
        val titulo = intent.getStringExtra("titulo").toString()
        val descricao = intent.getStringExtra("descricao").toString()

        tituloDetalhe.setText(titulo);
        descricaoDetalhe.setText(descricao);


    }

    fun editarReceita(v: View) {
        val id = intent.getStringExtra("id").toString()
        val titulo = tituloDetalhe.text.toString()
        val descricao = descricaoDetalhe.text.toString()
        val receita = Receita(id,titulo,descricao,"291551d5-260e-43d6-95dc-eb7b67358173")

        RetrofitClient.getInstance.create(ReceitaService::class.java).editarReceita(id, receita)
            .enqueue(object : Callback<Receita> {

                override fun onFailure(call: Call<Receita>, t: Throwable) {
                    Log.d("error", "onResponse: ${t.message}")
                }
                override fun onResponse(call: Call<Receita>, response: Response<Receita>) {

                    if(response.isSuccessful) {

                        Toast.makeText(applicationContext, "Receita alterada com Sucesso!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(baseContext, MinhaListaReceita::class.java))


                    }else {
                        Toast.makeText(baseContext, "Falha ao alterar Receita: ${response.errorBody()}", Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }


}