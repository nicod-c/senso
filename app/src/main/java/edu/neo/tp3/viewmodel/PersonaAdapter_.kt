package edu.neo.tp3.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import edu.neo.tp3.R
import edu.neo.tp3.model.Persona

class PersonaAdapter_ (private val mContext : Context,
                      private val listapersonas : List<Persona>): ArrayAdapter<Persona> (mContext, 0, listapersonas)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
       val layout = LayoutInflater.from(mContext).inflate(R.layout.persona_layout,parent,false)

        val persona = listapersonas[position]

        var tipodoc: TextView = layout.findViewById(R.id.t_persona_tipodoc)
        var numdoc: TextView = layout.findViewById(R.id.t_persona_numdoc)
        var nombre : TextView = layout.findViewById(R.id.t_persona_nombre)
        var apellido: TextView = layout.findViewById(R.id.t_persona_apellido)
        var fechanac: TextView = layout.findViewById(R.id.t_persona_fechanac)
        var edad : TextView = layout.findViewById(R.id.t_persona_edad)
        var sexo: TextView = layout.findViewById(R.id.t_persona_sexo)
        var telefono: TextView = layout.findViewById(R.id.t_persona_tel)
        var ocupacion: TextView = layout.findViewById(R.id.t_persona_ocupacion)
        var ingreso: TextView = layout.findViewById(R.id.t_persona_ingreso)

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

        return layout
    }
}