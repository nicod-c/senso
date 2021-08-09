package edu.neo.tp3.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import edu.neo.tp3.model.Persona
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@Dao
interface PersonaDao {

    @Query("SELECT * FROM personas ORDER BY Apellido")
    fun getAll() : LiveData<List<Persona>>

    @Query("SELECT*FROM personas WHERE idPersona = :id")
    fun getPersona(id: Int) : LiveData<Persona>

    @Query("SELECT * FROM personas WHERE Edad > 18 AND Trabaja = 0 OR Ocupacion = 'Desocupad@'")
    fun getDesocupados() : LiveData<List<Persona>>

    @Query("SELECT COUNT(DISTINCT idPersona) FROM personas WHERE Sexo = 'Masculino' AND Edad > 18 AND Trabaja = 0")
    fun countHDesocupados() : LiveData<Int>

    @Query("SELECT COUNT(DISTINCT idPersona) FROM personas WHERE Sexo = 'Femenino' AND Edad > 18 AND Trabaja = 0")
    fun countMDesocupadas() : LiveData<Int>

    @Query("SELECT COUNT(DISTINCT idPersona) FROM personas WHERE Sexo = 'Otro / No binario' AND Edad > 18 AND Trabaja = 0")
    fun countDesocupadxs() : LiveData<Int>

    @Query("SELECT * FROM personas WHERE ingresoMensual < 5000")
    fun getPobreza() : LiveData<List<Persona>>

    @Query("SELECT COUNT(DISTINCT idPersona) FROM personas WHERE Sexo = 'Masculino' AND ingresoMensual < 5000")
    fun countHpobreza() : LiveData<Int>

    @Query("SELECT COUNT(DISTINCT idPersona) FROM personas WHERE Sexo = 'Femenino' AND ingresoMensual < 5000")
    fun countMpobreza() : LiveData<Int>

    @Query("SELECT COUNT(DISTINCT idPersona) FROM personas WHERE Sexo = 'Otro / No binario' AND ingresoMensual < 5000")
    fun countNobinPobreza() : LiveData<Int>

    @Query("SELECT COUNT(DISTINCT idPersona) FROM personas WHERE Sexo = 'Masculino'")
    fun countHombres() : LiveData<Int>

    @Query("SELECT * FROM personas WHERE Sexo = 'Masculino'")
    fun getHombres()  : LiveData<List<Persona>>

    @Query("SELECT COUNT(DISTINCT idPersona) FROM personas WHERE Sexo = 'Femenino'")
    fun countMujeres() :  LiveData<Int>

    @Query("SELECT * FROM personas WHERE Sexo = 'Femenino'")
    fun getMujeres()  : LiveData<List<Persona>>

    @Query("SELECT COUNT(DISTINCT idPersona) FROM personas WHERE Sexo = 'Otro / No binario'")
    fun countNobinario() : LiveData<Int>

    @Query("SELECT * FROM personas WHERE Sexo = 'Otro / No binario'")
    fun getNobinario()  : LiveData<List<Persona>>

    @Insert
    fun insertAll(vararg personas : Persona)

    @Update
    fun update(persona: Persona)

    @Delete
    fun delete(persona: Persona)

}