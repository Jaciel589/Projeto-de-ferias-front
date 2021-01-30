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
import kotlinx.android.synthetic.main.activity_excluir_receita.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExcluirReceita : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_excluir_receita)

        supportActionBar?.title = "Excluir receita"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        val id = intent.getStringExtra("id").toString()
        val titulo = intent.getStringExtra("titulo").toString()
        val descricao = intent.getStringExtra("descricao").toString()

        tituloExcluir.setText(titulo);
        descricaoExcluir.setText(descricao);

    }

    fun excluirReceita(v: View) {
        val id = intent.getStringExtra("id").toString()

        RetrofitClient.getInstance.create(ReceitaService::class.java).excluirReceita(id)
            .enqueue(object : Callback<Receita> {

                override fun onFailure(call: Call<Receita>, t: Throwable) {
                    Log.d("error", "onResponse: ${t.message}")
                }
                override fun onResponse(call: Call<Receita>, response: Response<Receita>) {

                    if(response.isSuccessful) {
                        Toast.makeText(applicationContext, "Receita excluido com Sucesso!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(baseContext, MinhaListaReceita::class.java))

                    }else {
                        Toast.makeText(baseContext, "Falha ao excluir Receita: ${response.errorBody()}", Toast.LENGTH_SHORT).show()
                    }
                }
            })

    }
}