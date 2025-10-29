package br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.databinding.ActivityImcResultBinding

class ImcResultActivity : AppCompatActivity() {

    private val airb: ActivityImcResultBinding by lazy {
        ActivityImcResultBinding.inflate(layoutInflater)
    }

    private lateinit var nameTv: TextView
    private lateinit var resultTv: TextView
    private lateinit var categoryTv: TextView
    private lateinit var returnBt: Button
    private lateinit var submitBt: Button

    private fun calculateCategory(imc: Double): String {
        if (imc >= 30) return "Obesidade"
        if (imc >= 25) return "Sobrepeso"
        if (imc >= 18.5) return "Normal"
        return "Abaixo do peso"
    }

    private fun advance() {
        val name = intent.getStringExtra("EXTRA_NAME")
        val age = intent.getIntExtra("EXTRA_AGE", 0)
        val height = intent.getDoubleExtra("EXTRA_HEIGHT", 0.0)
        val weight = intent.getDoubleExtra("EXTRA_WEIGHT", 0.0)
        val sex = intent.getStringExtra("EXTRA_SEX")
        val activityLevel = intent.getStringExtra("EXTRA_ACTIVITY_LEVEL")
        val imc = intent.getDoubleExtra("EXTRA_IMC", 0.0)

        val category = calculateCategory(imc)

        nameTv.text = name
        resultTv.text = imc.toString()
        categoryTv.text = category

        val intentCR = Intent(this, CaloricResultActivity::class.java)
        intentCR.putExtra("EXTRA_NAME", name)
        intentCR.putExtra("EXTRA_AGE", age)
        intentCR.putExtra("EXTRA_HEIGHT", height)
        intentCR.putExtra("EXTRA_WEIGHT", weight)
        intentCR.putExtra("EXTRA_SEX", sex)
        intentCR.putExtra("EXTRA_ACTIVITY_LEVEL", activityLevel)
        intentCR.putExtra("EXTRA_IMC", imc)

        startActivity(intentCR)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(airb.root)

        nameTv = airb.imcNameTv
        resultTv = airb.imcResultValueTv
        categoryTv = airb.imcCategoryValueTv

        returnBt = airb.imcReturnBt
        submitBt = airb.imcSubmitBt

        returnBt.setOnClickListener { finish() }
        submitBt.setOnClickListener { advance() }
    }
}