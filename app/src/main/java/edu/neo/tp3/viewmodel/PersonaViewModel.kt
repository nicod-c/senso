package edu.neo.tp3.viewmodel

import android.content.Context
import android.content.Intent
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edu.neo.tp3.dao.AppDataBase
import edu.neo.tp3.model.Persona
import edu.neo.tp3.view.PersonaActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PersonaViewModel: ViewModel() {

    fun CalcularEdad(fechaNac: String) : Int{

        var fechaNacDate = SimpleDateFormat("dd/MM/yyyy").parse(fechaNac)
        var fechaActual = Date(System.currentTimeMillis())
        var difFechaMili = fechaActual.time - fechaNacDate.time
        var segundos = difFechaMili / 1000
        var min = segundos / 60
        var horas = min / 60
        var dias = horas / 24
        var anios = dias / 365
        var edad = anios.toInt()
        return edad
    }

    fun getAll(context : Context, lista : List<Persona>, activity: AppCompatActivity, listview: ListView) {
        val database = AppDataBase.getDataBase(context)
        var listapersonas = lista
        database.personas().getAll().observe(activity, {
            listapersonas = it
            val adapter = PersonaAdapter_(context,listapersonas)
            listview.adapter = adapter
            listview.setOnItemClickListener { parent, view, position, id ->
                val intent = Intent(context,PersonaActivity::class.java)
                intent.putExtra("id",listapersonas[position].idPersona)
                startActivity(context,intent,null)
            }
        })

    }

    fun getDesocupados(context : Context, lista : List<Persona>, activity: AppCompatActivity, listview: ListView) {
        val database = AppDataBase.getDataBase(context)
        database.personas().getDesocupados().observe(activity, {
            var listapersonas = lista
            listapersonas = it
            val adapter = PersonaAdapter_(context,listapersonas)
            listview.adapter = adapter
            listview.setOnItemClickListener { parent, view, position, id ->
                val intent = Intent(context,PersonaActivity::class.java)
                intent.putExtra("id",listapersonas[position].idPersona)
                startActivity(context,intent,null)
            }
        })
    }

    fun getHombres(context : Context, lista : List<Persona>, activity: AppCompatActivity, listview: ListView) {

        val database = AppDataBase.getDataBase(context)

        database.personas().getHombres().observe(activity, {
            var listapersonas = lista
            listapersonas = it
            val adapter = PersonaAdapter_(context,listapersonas)
            listview.adapter = adapter
            listview.setOnItemClickListener { parent, view, position, id ->
                val intent = Intent(context,PersonaActivity::class.java)
                intent.putExtra("id",listapersonas[position].idPersona)
                startActivity(context,intent,null)
            }
        })
    }


    fun getMujeres(context : Context, lista : List<Persona>, activity: AppCompatActivity, listview: ListView) {

        val database = AppDataBase.getDataBase(context)

        database.personas().getMujeres().observe(activity, {
            var listapersonas = lista
            listapersonas = it
            val adapter = PersonaAdapter_(context,listapersonas)
            listview.adapter = adapter
            listview.setOnItemClickListener { parent, view, position, id ->
                val intent = Intent(context,PersonaActivity::class.java)
                intent.putExtra("id",listapersonas[position].idPersona)
                startActivity(context,intent,null)
            }
        })

    }


    fun getNobinarios(context : Context, lista : List<Persona>, activity: AppCompatActivity, listview: ListView) {

        val database = AppDataBase.getDataBase(context)

        database.personas().getNobinario().observe(activity, {
            var listapersonas = lista
            listapersonas = it
            val adapter = PersonaAdapter_(context,listapersonas)
            listview.adapter = adapter
            listview.setOnItemClickListener { parent, view, position, id ->
                val intent = Intent(context,PersonaActivity::class.java)
                intent.putExtra("id",listapersonas[position].idPersona)
                startActivity(context,intent,null)
            }
        })

    }


    fun getPobreza(context : Context, lista : List<Persona>, activity: AppCompatActivity, listview: ListView) {

        val database = AppDataBase.getDataBase(context)

        database.personas().getPobreza().observe(activity, {
            var listapersonas = lista
            listapersonas = it
            val adapter = PersonaAdapter_(context,listapersonas)
            listview.adapter = adapter
            listview.setOnItemClickListener { parent, view, position, id ->
                val intent = Intent(context,PersonaActivity::class.java)
                intent.putExtra("id",listapersonas[position].idPersona)
                startActivity(context,intent,null)
            }
        })
    }


    fun contarMujeres(context: Context,activity: AppCompatActivity, text: TextView ){

        val database = AppDataBase.getDataBase(context)

        database.personas().countMujeres().observe(activity,  {
            text.setText(it.toString())
        })
    }


    fun contarMujeresdesocupadas(context: Context,activity: AppCompatActivity, text: TextView ){

        val database = AppDataBase.getDataBase(context)

        database.personas().countMDesocupadas().observe(activity,  {
            text.setText(it.toString())
        })
    }


    fun contarMujerespobreza(context: Context,activity: AppCompatActivity, text: TextView ){

        val database = AppDataBase.getDataBase(context)

        database.personas().countMpobreza().observe(activity,  {
            text.setText(it.toString())
        })
    }

    fun contarHombres(context: Context,activity: AppCompatActivity, text: TextView ){
        val database = AppDataBase.getDataBase(context)
        database.personas().countHombres().observe(activity,  {
            text.setText(it.toString())
        })
    }


    fun contarHombresdescoupados(context: Context,activity: AppCompatActivity, text: TextView ){

        val database = AppDataBase.getDataBase(context)

        database.personas().countHDesocupados().observe(activity,  {
            text.setText(it.toString())
        })
    }


    fun contarHombrespobreza(context: Context,activity: AppCompatActivity, text: TextView ){

        val database = AppDataBase.getDataBase(context)

        database.personas().countHpobreza().observe(activity,  {
            text.setText(it.toString())
        })
    }


    fun contarNobinarios(context: Context,activity: AppCompatActivity, text: TextView ){

        val database = AppDataBase.getDataBase(context)

        database.personas().countNobinario().observe(activity,  {
            text.setText(it.toString())
        })
    }


    fun contarDesocupadxs(context: Context,activity: AppCompatActivity, text: TextView ){

        val database = AppDataBase.getDataBase(context)

        database.personas().countDesocupadxs().observe(activity,  {
            text.setText(it.toString())
        })
    }


    fun contarNobinariosPobreza(context: Context,activity: AppCompatActivity, text: TextView ){

        val database = AppDataBase.getDataBase(context)

        database.personas().countNobinPobreza().observe(activity,  {
            text.setText(it.toString())
        })
    }

    fun sensar(context: Context, activity: AppCompatActivity, persona: Persona) {
        val database = AppDataBase.getDataBase(context)

        CoroutineScope(Dispatchers.IO).launch {
            database.personas().insertAll(persona)
            activity.finish()
        }
    }

    fun borrar(context: Context, activity: AppCompatActivity, persona: Persona) {
        val database = AppDataBase.getDataBase(context)

        CoroutineScope(Dispatchers.IO).launch {
            database.personas().delete(persona)
            activity.finish()
        }
    }
}