package com.example.projeto_de_ferias.Activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projeto_de_ferias.Adapter.ReceitaAdapter
import com.example.projeto_de_ferias.Models.Receita
import com.example.projeto_de_ferias.R
import com.example.projeto_de_ferias.Retrofit.RetrofitClient
import com.example.projeto_de_ferias.Service.ReceitaService
import com.example.projeto_de_ferias.SharedPreference.MyPreference
import kotlinx.android.synthetic.main.activity_minha_lista_receita.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MinhaListaReceita : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_minha_lista_receita)

        supportActionBar?.title = " Minhas Receitas"

       val idUser: String = MyPreference.getIdUsuario(baseContext);

        RetrofitClient.getInstance.create(ReceitaService::class.java)
            .getReceitas(idUser).enqueue(object : Callback<ArrayList<Receita>> {
                override fun onFailure(call: Call<ArrayList<Receita>>, t: Throwable) {
                    println("Dados" + t.message)

                }
                override fun onResponse(call: Call<ArrayList<Receita>>, response: Response<ArrayList<Receita>>) {
                    if (response.isSuccessful) {
                        showData(response.body()!!)
                    }
                }
            })

    }

    private fun showData(response: ArrayList<Receita>) {
       println("Dados" + response)
        lateinit var adapter : ReceitaAdapter

        adapter = ReceitaAdapter(response)
        recyclerView.layoutManager = LinearLayoutManager(this.baseContext)
        recyclerView.adapter = adapter

    }

    fun cadastrarReceita(v: View) {
        startActivity(Intent(baseContext, CadastroReceita::class.java))

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_sair -> logout()

        }
        return super.onOptionsItemSelected(item)
    }
    private fun logout() {
        MyPreference.deleteToken(baseContext)
        startActivity(Intent(this.baseContext, MainActivity::class.java))
    }

}