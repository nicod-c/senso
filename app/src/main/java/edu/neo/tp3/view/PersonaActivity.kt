package edu.neo.tp3.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import edu.neo.tp3.R
import edu.neo.tp3.dao.AppDataBase
import edu.neo.tp3.model.Persona
import edu.neo.tp3.viewmodel.PersonaViewModel


class PersonaActivity : AppCompatActivity() {

    lateinit var database : AppDataBase
    lateinit var persona : Persona
    lateinit var personalivedata: LiveData<Persona>
    lateinit var borrar : ImageButton
    lateinit var editar : ImageButton
    lateinit var tipodoc: TextView
    lateinit var numdoc: TextView
    lateinit var nombre : TextView
    lateinit var apellido: TextView
    lateinit var fechanac: TextView
    lateinit var edad : TextView
    lateinit var sexo: TextView
    lateinit var telefono: TextView
    lateinit var ocupacion: TextView
    lateinit var ingreso: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_persona)

        inicializar()
        database = AppDataBase.getDataBase(this)

        val personaVM = ViewModelProvider(this).get(PersonaViewModel::class.java)

        val idProducto = intent.getIntExtra("id",0)

        personalivedata = database.personas().getPersona(idProducto)

        personalivedata.observe(this, Observer {
            persona = it
            tipodoc.text = persona.tipoDoc
            numdoc.text = persona.numDoc
            nombre.text = persona.Nombre
            apellido.text = persona.Apellido
            fechanac.text = persona.fechaNac
            edad.text = persona.Edad.toString()
            sexo.text = persona.Sexo
            telefono.text = persona.Telefono
            ocupacion.text = persona.Ocupacion
            ingreso.text = persona.ingresoMensual.toString()


        })

        borrar.setOnClickListener(
            View.OnClickListener {
               personalivedata.removeObservers(this)
                personaVM.borrar(this,this,persona)
                Toast.makeText(applicationContext, "PERSONA BORRADA", Toast.LENGTH_SHORT).show()
            }
        )

        editar.setOnClickListener(
            View.OnClickListener {
                val intent = Intent(this,SensarActivity::class.java)
                intent.putExtra("persona",persona)
                startActivity(intent)
            }
        )


    }

    private fun inicializar(){
        tipodoc = findViewById(R.id.t_persona_tipodoc)
        numdoc = findViewById(R.id.t_persona_numdoc)
        nombre  = findViewById(R.id.t_persona_nombre)
        apellido = findViewById(R.id.t_persona_apellido)
        fechanac = findViewById(R.id.t_persona_fechanac)
        edad  = findViewById(R.id.t_persona_edad)
        sexo = findViewById(R.id.t_persona_sexo)
        telefono = findViewById(R.id.t_persona_tel)
        ocupacion = findViewById(R.id.t_persona_ocupacion)
        ingreso = findViewById(R.id.t_persona_ingreso)
        borrar = findViewById(R.id.b_delete)
        editar = findViewById(R.id.b_edit)
    }

}