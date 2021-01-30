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
import com.example.projeto_de_ferias.SharedPreference.MyPreference
import kotlinx.android.synthetic.main.activity_cadastro_receita.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroReceita : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_receita)

        supportActionBar?.title = "Cadastre uma receita"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun cadastrarReceita(v: View) {
        val titulo = tituloCadastro.text.toString()
        val descricao = descricaoCadastro.text.toString()
        val user_id: String = MyPreference.getIdUsuario(baseContext);

        val receita = Receita("",titulo,descricao, user_id)
        RetrofitClient.getInstance.create(ReceitaService::class.java).postReceita(receita).enqueue(
            object : Callback<Receita> {
                override fun onFailure(call: Call<Receita>, t: Throwable) {
                    Log.d("error", "onFailure: ${t.message}")
                }
                override fun onResponse(call: Call<Receita>, response: Response<Receita>) {
                    if (response.isSuccessful) {
                        Log.d("sucesso", "onResponse: ${response.body()}")

                        Toast.makeText(applicationContext, "Receita Criada com Sucesso!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(baseContext, MinhaListaReceita::class.java))
                    }
                }
            }
        )
    }
}