package com.example.projeto_de_ferias.Activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.projeto_de_ferias.Models.Usuario
import com.example.projeto_de_ferias.R
import com.example.projeto_de_ferias.Retrofit.RetrofitClient
import com.example.projeto_de_ferias.Service.UsuarioService
import kotlinx.android.synthetic.main.activity_cadastro.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.system.measureTimeMillis

class Cadastro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        supportActionBar?.title = " Cadastro de usuário"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    fun cadastrarUsuario(v: View) {
        val nome = nomeCadastro.text.toString()
        val email = emailCadastro.text.toString()
        val senha = senhaCadastro.text.toString()

        val usuario = Usuario("",nome, email, senha)
        RetrofitClient.getInstance.create(UsuarioService::class.java).postUsuario(usuario).enqueue(
            object : Callback<Usuario> {
                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    Log.d("error", "onFailure: ${t.message}")
                }
                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                    if (response.isSuccessful) {
                        Log.d("sucesso", "onResponse: ${response.body()}")

                        Toast.makeText(applicationContext, "Usuario Criado com Sucesso!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(baseContext, Login::class.java))
                    }
                }
            }
        )
    }

    fun voltarParaTelaDeLogin(v: View) {
        startActivity(Intent(baseContext, Login::class.java))
    }
}