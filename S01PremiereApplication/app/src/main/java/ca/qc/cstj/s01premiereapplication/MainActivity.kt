package ca.qc.cstj.s01premiereapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPremier = findViewById<Button>(R.id.btnPremier)


        btnPremier.setOnClickListener {
            Toast.makeText(this, "Bonjour", Toast.LENGTH_LONG).show()
        }

    }


}