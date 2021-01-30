package com.example.projeto_de_ferias.Activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.projeto_de_ferias.R
import com.example.projeto_de_ferias.SharedPreference.MyPreference

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val idUser: String = MyPreference.getIdUsuario(baseContext);

        Handler(Looper.getMainLooper()).postDelayed({
            if(idUser != "null")
                startActivity(Intent(this.baseContext,MinhaListaReceita::class.java))
            else
                startActivity(Intent(this.baseContext,Login::class.java))
        }, 4000)
    }


}