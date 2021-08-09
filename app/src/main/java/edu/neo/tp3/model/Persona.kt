package edu.neo.tp3.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "personas")
class Persona (var tipoDoc: String, var numDoc: String, var Nombre: String,
               var Apellido: String, var fechaNac : String, var Edad: Int, var Sexo : String,
               var Telefono: String, var Trabaja : Boolean, var Ocupacion : String, var ingresoMensual: Int,
               @PrimaryKey(autoGenerate = true) var idPersona : Int = 0)
    : Serializable