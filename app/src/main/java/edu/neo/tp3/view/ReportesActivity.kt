package edu.neo.tp3.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import edu.neo.tp3.R
import edu.neo.tp3.dao.AppDataBase
import edu.neo.tp3.model.Persona
import edu.neo.tp3.viewmodel.PersonaAdapter_
import edu.neo.tp3.viewmodel.PersonaViewModel


class ReportesActivity : AppCompatActivity() {

    lateinit var listartodos: Button
    lateinit var listardesempleados: Button
    lateinit var listarpobreza: Button
    lateinit var listarmujeres: Button
    lateinit var listarhombres: Button
    lateinit var listarnobinarios: Button
    lateinit var cantHombres: TextView
    lateinit var cantMujeres: TextView
    lateinit var cantNobinarios: TextView
    lateinit var lv_listado: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reportes)

        inicializar()

        val personavm = ViewModelProvider(this).get(PersonaViewModel::class.java)
        var listaPersonas = emptyList<Persona>()


        listartodos.setOnClickListener(
            View.OnClickListener {
                personavm.getAll(this, listaPersonas, this, lv_listado)
                personavm.contarHombres(this, this, cantHombres)
                personavm.contarMujeres(this, this, cantMujeres)
                personavm.contarNobinarios(this, this, cantNobinarios)

            }

        )


        listardesempleados.setOnClickListener(
            View.OnClickListener {

                personavm.getDesocupados(this, listaPersonas, this, lv_listado)
                personavm.contarMujeresdesocupadas(this, this, cantMujeres)
                personavm.contarHombresdescoupados(this, this, cantHombres)
                personavm.contarDesocupadxs(this, this, cantNobinarios)

            }
        )

        listarpobreza.setOnClickListener(
            View.OnClickListener {

                personavm.getPobreza(this, listaPersonas, this, lv_listado)
                personavm.contarMujerespobreza(this, this, cantMujeres)
                personavm.contarHombrespobreza(this, this, cantHombres)
                personavm.contarNobinariosPobreza(this, this, cantNobinarios)

            }
        )

        listarmujeres.setOnClickListener(
            View.OnClickListener {

                personavm.getMujeres(this, listaPersonas, this, lv_listado)
                personavm.contarMujeres(this, this, cantMujeres)
                cantHombres.setText(0.toString())
                cantNobinarios.setText(0.toString())
            }
        )

        listarhombres.setOnClickListener(
            View.OnClickListener {

                personavm.getHombres(this, listaPersonas, this, lv_listado)
                personavm.contarHombres(this, this, cantHombres)
                cantMujeres.setText(0.toString())
                cantNobinarios.setText(0.toString())
            }
        )

        listarnobinarios.setOnClickListener(
            View.OnClickListener {

                personavm.getNobinarios(this, listaPersonas, this, lv_listado)
                personavm.contarNobinarios(this, this, cantNobinarios)
                cantMujeres.setText(0.toString())
                cantHombres.setText(0.toString())
            }
        )


    }


    private fun inicializar() {
        listartodos = findViewById(R.id.b_lista_completa)
        lv_listado = findViewById(R.id.lv_listadopersonas)
        listardesempleados = findViewById(R.id.b_desocupados)
        listarpobreza = findViewById(R.id.b_pobreza)
        listarmujeres = findViewById(R.id.b_mujeres)
        listarhombres = findViewById(R.id.b_hombres)
        listarnobinarios = findViewById(R.id.b_nobinario)
        cantHombres = findViewById(R.id.t_canthombres)
        cantMujeres = findViewById(R.id.t_cantmujeres)
        cantNobinarios = findViewById(R.id.t_cantnobinarios)
    }

}