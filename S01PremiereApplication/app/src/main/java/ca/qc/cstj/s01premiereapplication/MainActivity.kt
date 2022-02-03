package ca.qc.cstj.s01premiereapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Équivalent de document.getElementById
        val btnPremier = findViewById<Button>(R.id.btnPremier)
        val btnTelephone = findViewById<Button>(R.id.btnTelephone)
        val btnSMS =  findViewById<Button>(R.id.btnSMS)
        val btnJeu = findViewById<Button>(R.id.btnJeu)
        val edtName = findViewById<EditText>(R.id.edtName)

        btnJeu.setOnClickListener {
            val jeuIntent = SecondActivity.newIntent(this, edtName.text.toString())
            startActivity(jeuIntent)
        }

        btnSMS.setOnClickListener {
            val smsIntent = Intent(Intent.ACTION_VIEW, Uri.parse("smsto:450-436-1580"))
            smsIntent.putExtra("sms_body", "Bonjour de ${edtName.text}" )
            startActivity(smsIntent)
        }

        btnPremier.setOnClickListener {
            Toast.makeText(this, "Bonjour", Toast.LENGTH_LONG).show()
        }

        btnTelephone.setOnClickListener {
            val phoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:450-436-1580"))
            startActivity(phoneIntent)
        }


    }


}