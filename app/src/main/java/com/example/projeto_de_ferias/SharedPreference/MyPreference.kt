package com.example.projeto_de_ferias.SharedPreference

import android.content.Context
import com.example.projeto_de_ferias.Util.Constantes

object MyPreference {

    fun setIdUsuario(context: Context, legato: String) {
        val preference =
            context.getSharedPreferences(Constantes.MY_PREFERENCE, Context.MODE_PRIVATE)
                .edit()
        preference.apply {
            putString(Constantes.ID_USUARIO, legato)
        }.apply()
    }

    fun getIdUsuario(context: Context): String {
        return context.getSharedPreferences(
            Constantes.MY_PREFERENCE,
            Context.MODE_PRIVATE
        ).getString(Constantes.ID_USUARIO, null).toString()
    }
    fun deleteToken (context: Context) {
        context.getSharedPreferences(Constantes.MY_PREFERENCE, Context.MODE_PRIVATE)
            .edit().clear().apply()
    }
}