package br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus

import android.content.Intent
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
        val name = intent.getStringExtra("EXTRA_NAME")
        val age = intent.getIntExtra("EXTRA_AGE", 0)
        val height = intent.getDoubleExtra("EXTRA_HEIGHT", 0.0)
        val weight = intent.getDoubleExtra("EXTRA_WEIGHT", 0.0)
        val sex = intent.getStringExtra("EXTRA_SEX")
        val activityLevel = intent.getStringExtra("EXTRA_ACTIVITY_LEVEL")
        val imc = intent.getDoubleExtra("EXTRA_IMC", 0.0)
        val category = intent.getStringExtra("EXTRA_CATEGORY")

        val tmb = calculateTmb(sex ?: "Masculino", weight, height, age)

        nameTv.text = name
        resultTv.text = tmb.toString()

        val intentIW = Intent(this, IdealWeightActivity::class.java)
        intentIW.putExtra("EXTRA_NAME", name)
        intentIW.putExtra("EXTRA_AGE", age)
        intentIW.putExtra("EXTRA_HEIGHT", height)
        intentIW.putExtra("EXTRA_WEIGHT", weight)
        intentIW.putExtra("EXTRA_SEX", sex)
        intentIW.putExtra("EXTRA_ACTIVITY_LEVEL", activityLevel)
        intentIW.putExtra("EXTRA_IMC", imc)
        intentIW.putExtra("EXTRA_CATEGORY", category)
        intentIW.putExtra("EXTRA_TMB", tmb)

        startActivity(intentIW)
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