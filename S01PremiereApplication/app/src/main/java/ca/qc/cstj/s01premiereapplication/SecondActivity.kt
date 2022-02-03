package ca.qc.cstj.s01premiereapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast

const val MIN_VALUE = 0
const val MAX_VALUE = 100

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val txvWelcome = findViewById<TextView>(R.id.txvWelcome)
        val nprNumber = findViewById<NumberPicker>(R.id.nprNumber)
        val btnTestNumber = findViewById<TextView>(R.id.btnTestNumber)

        txvWelcome.text = getString(R.string.deviner_le_nombre, intent.getStringExtra("EXTRA_SECOND_ACTIVITY_NAME"))

        nprNumber.minValue = MIN_VALUE
        nprNumber.maxValue = MAX_VALUE

        val theNumber = (0..100).random()

        btnTestNumber.setOnClickListener {

            when {
                nprNumber.value == theNumber -> {
                    Toast.makeText(this, getString(R.string.msgWinner), Toast.LENGTH_LONG).show()
                }
                nprNumber.value > theNumber -> {
                    Toast.makeText(this, R.string.msgLess, Toast.LENGTH_LONG).show()
                }
                else -> {
                    // Choix < que réponse
                    Toast.makeText(this, R.string.msgHigh, Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    //partie statique de la classe
    companion object {
        fun newIntent(context: Context, name: String) : Intent {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("EXTRA_SECOND_ACTIVITY_NAME", name)
            return intent
        }
    }
}