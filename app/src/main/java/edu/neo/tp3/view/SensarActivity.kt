package edu.neo.tp3.view

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.neo.tp3.R
import edu.neo.tp3.dao.AppDataBase
import edu.neo.tp3.model.Persona
import edu.neo.tp3.viewmodel.PersonaViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import kotlin.coroutines.coroutineContext

class SensarActivity : AppCompatActivity() {

    lateinit var tipodoc : Spinner
    lateinit var numdoc : EditText
    lateinit var nombre: EditText
    lateinit var apellido: EditText
    lateinit var fechanac: EditText
    lateinit var sexo: RadioGroup
    lateinit var fem: RadioButton
    lateinit var masc: RadioButton
    lateinit var otro: RadioButton
    lateinit var tel: EditText
    lateinit var trabaja: Switch
    lateinit var ocupacion: EditText
    lateinit var ingresos: EditText
    lateinit var guardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensar)


        val personaVM = ViewModelProvider(this).get(PersonaViewModel::class.java)
        val database = AppDataBase.getDataBase(this)
        inicializar()
        inicializarSpinner()

        val tipoDocs = arrayOf("LIBRETA CIVICA", "LIBRETA DE ENROLAMIENTO", "DNI LIBRETA VERDE",
            "DNI LIBRETA CELESTE", "DNI TARJETA", "DNI EXTRANJERO", "PASAPORTE")

        var idPersona : Int? = null

        if(intent.hasExtra("persona")){
            val persona = intent.extras?.getSerializable("perosna") as Persona

            numdoc.setText(persona.numDoc)
            nombre.setText(persona.Nombre)
            apellido.setText(persona.Apellido)
            fechanac.setText(persona.fechaNac)
            tel.setText(persona.Telefono)
            ocupacion.setText(persona.Ocupacion)
            ingresos.setText(persona.ingresoMensual)
        }

        guardar.setOnClickListener(
            View.OnClickListener {

                var tipoDocseleccionado = String()

                tipodoc.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                           tipoDocseleccionado = tipoDocs[position]
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        Toast.makeText(applicationContext, "seleccione un doc",Toast.LENGTH_SHORT).show()
                    }
                }

                val selected: Int = sexo!!.checkedRadioButtonId
                val rb_selected: RadioButton = findViewById(selected)

                var empleo = String()

                if( trabaja.isChecked == false){
                    empleo = "Desocupad@"
                    ocupacion.setText(empleo.toString())
                    ingresos.setText(0.toString())
                }

                val persona = Persona(
                tipoDocseleccionado,
                numdoc.text.toString(),
                nombre.text.toString(),
                apellido.text.toString(),
                fechanac.text.toString(),
                personaVM.CalcularEdad(fechanac.text.toString()),
                    rb_selected.text.toString(),
                    tel.text.toString(),
                    trabaja.isChecked,
                    ocupacion?.text.toString(),
                    ingresos?.text.toString().toInt()
                )

                if(idPersona != null){
                    CoroutineScope(Dispatchers.IO).launch {
                        persona.idPersona = idPersona
                        database.personas().update(persona)
                        this@SensarActivity.finish()
                    }
                }

                else

                personaVM.sensar(this,this, persona)

                Toast.makeText(applicationContext, "PERSONA SENSADA",Toast.LENGTH_SHORT).show()
            }

        )


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    private fun inicializarSpinner(){
        val tipoDocs = arrayOf("--Seleccionar Tipo Doc--", "LIBRETA CIVICA", "LIBRETA DE ENROLAMIENTO", "DNI LIBRETA VERDE",
            "DNI LIBRETA CELESTE", "DNI TARJETA", "DNI EXTRANJERO", "PASAPORTE")

        tipodoc.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, tipoDocs)
    }

    private fun inicializar(){
        tipodoc = findViewById(R.id.spinner)
         numdoc = findViewById(R.id.e_num_doc)
         nombre = findViewById(R.id.e_nombre)
         apellido = findViewById(R.id.e_apellido)
         fechanac = findViewById(R.id.e_fechaNac)
         sexo = findViewById(R.id.rg_sexo)
         fem = findViewById(R.id.rb_fem)
         masc = findViewById(R.id.rb_masc)
         otro = findViewById(R.id.rb_otro)
         tel = findViewById(R.id.e_telefono)
         trabaja = findViewById(R.id.switch_trabaja)
         ocupacion = findViewById(R.id.e_ocupacion)
         ingresos = findViewById(R.id.e_ingresos)
        guardar = findViewById(R.id.b_guardar)
    }

}