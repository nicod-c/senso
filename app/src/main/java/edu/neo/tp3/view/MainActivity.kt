package edu.neo.tp3.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import edu.neo.tp3.R

class MainActivity : AppCompatActivity() {

    lateinit var sensar: Button
    lateinit var reportes: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            inicializar()

        sensar.setOnClickListener(
            View.OnClickListener {

                val intent = Intent(this, SensarActivity::class.java)
                startActivity(intent)

            })


        reportes.setOnClickListener(
            View.OnClickListener {

                val intent = Intent(this, ReportesActivity::class.java)
                startActivity(intent)

            })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    /*
    override fun onOptionsItemSelected(item: MenuItem): Boolean  {
        return when(item.itemId){

        }

    }
*/

    private fun inicializar(){
        sensar = findViewById(R.id.b_sensar)
        reportes = findViewById(R.id.b_listados)
    }

}