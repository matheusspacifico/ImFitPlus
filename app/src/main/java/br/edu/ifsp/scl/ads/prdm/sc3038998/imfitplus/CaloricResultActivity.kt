package br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.databinding.ActivityCaloricResultBinding

class CaloricResultActivity : AppCompatActivity() {

    private val acrb: ActivityCaloricResultBinding by lazy {
        ActivityCaloricResultBinding.inflate(layoutInflater)
    }

    private lateinit var nameTv: TextView
    private lateinit var resultTv: TextView
    private lateinit var returnBt: Button
    private lateinit var submitBt: Button

    private fun calculateTmb(sex: String, weight: Double, height: Double, age: Int): Double {
        if (sex == "Masculino") {
            return 66 + (13.7 * weight) + (5 * height * 100) - (6.8 * age)
        }

        return 655 + (9.6 * weight) + (1.8 * height * 100) - (4.7 * age)
    }
    private fun advance() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(acrb.root)

        nameTv = acrb.caloricNameTv
        resultTv = acrb.caloricTmbValueTv
        returnBt = acrb.caloricReturnBt
        submitBt = acrb.caloricSubmitBt

        returnBt.setOnClickListener { finish() }
        submitBt.setOnClickListener { advance() }
    }
}