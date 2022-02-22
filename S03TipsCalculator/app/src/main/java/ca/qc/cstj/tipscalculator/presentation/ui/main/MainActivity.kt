package ca.qc.cstj.tipscalculator.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import ca.qc.cstj.tipscalculator.R
import ca.qc.cstj.tipscalculator.core.Formatter
import ca.qc.cstj.tipscalculator.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener {
            try {
                val subtotal = binding.tilSubtotal.editText!!.text.toString().toDouble()
                val tips = binding.tilTips.editText!!.text.toString().toInt()
                viewModel.calculate(subtotal, tips)
            } catch (ex: NumberFormatException) {
                Toast.makeText(this,"Erreur", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.tipsCalculation.observe(this) {
            binding.lblTips.text = Formatter.toMoneyFormat(it.tipsAmount)
            binding.lblTPS.text = Formatter.toMoneyFormat(it.TPS)
            binding.lblTVQ.text = Formatter.toMoneyFormat(it.TVQ)
            binding.lblTotal.text = Formatter.toMoneyFormat(it.total)
        }
    }


}