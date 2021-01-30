package com.example.projeto_de_ferias.Activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.projeto_de_ferias.Models.Auth
import com.example.projeto_de_ferias.R
import com.example.projeto_de_ferias.Retrofit.RetrofitClient
import com.example.projeto_de_ferias.Service.UsuarioService
import com.example.projeto_de_ferias.SharedPreference.MyPreference
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.title = "Login"
    }
    fun irParaTelaDeCadastro(v: View) {
        startActivity(Intent(baseContext, Cadastro::class.java))

    }

    fun entrar(v: View){
        val email = emailLogin.text.toString()
        val senha = senhaLogin.text.toString()

        val auth = Auth()
        auth.email = email;
        auth.senha = senha;

        RetrofitClient.getInstance.create(UsuarioService::class.java).loginUsuario(auth).enqueue(
            object : Callback<Auth> {
                override fun onFailure(call: Call<Auth>, t: Throwable) {
                    Log.d("error", "onFailure: ${t.message}")
                    Toast.makeText(applicationContext, "Login ou senha invalido!", Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(call: Call<Auth>, response: Response<Auth>) {
                    if (response.isSuccessful){

                        val auth : Auth = response.body()!!
                       MyPreference.setIdUsuario(baseContext, auth.id);
                      startActivity(Intent(baseContext, MinhaListaReceita::class.java))
                    }
                }
            }
        )
    }


}