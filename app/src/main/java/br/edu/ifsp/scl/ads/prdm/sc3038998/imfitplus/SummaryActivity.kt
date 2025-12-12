package br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.databinding.ActivitySummaryBinding
import br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.entity.User
import br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.entity.UserCalculationRecord
import br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.persistence.AppDatabase
import java.util.Locale
import kotlinx.coroutines.launch

class SummaryActivity : AppCompatActivity() {

    private val asb: ActivitySummaryBinding by lazy {
        ActivitySummaryBinding.inflate(layoutInflater)
    }

    private lateinit var nameTv: TextView
    private lateinit var imcTv: TextView
    private lateinit var categoryTv: TextView
    private lateinit var idealTv: TextView
    private lateinit var tmbTv: TextView
    private lateinit var waterTv: TextView
    private lateinit var returnBt: Button
    private lateinit var submitBt: Button

    private fun calculateWater(weight: Double): Double {
        return weight * 350
    }

    private fun saveToDatabase(
        name: String,
        age: Int,
        height: Double,
        weight: Double,
        sex: String,
        activityLevel: String,
        imc: Double,
        category: String,
        tmb: Double,
        ideal: Double
    ) {
        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(applicationContext)
            val userDao = db.userDao()
            val userCalculationRecordDao = db.userCalculationRecordDao()

            val user = User(
                name = name,
                age = age,
                height = height,
                weight = weight,
                activityLevel = activityLevel
            )

            val userId = userDao.insertUser(user)

            val record = UserCalculationRecord(
                userId = userId,
                name = name,
                age = age,
                sex = sex,
                height = height,
                weight = weight,
                activityLevel = activityLevel,
                imc = imc,
                imcCategory = category,
                tmb = tmb,
                idealWeight = ideal
            )

            userCalculationRecordDao.insertUserCalculationRecord(record)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(asb.root)

        nameTv = asb.summaryNameTv
        imcTv = asb.summaryImcValueTv
        categoryTv = asb.summaryCategoryValueTv
        idealTv = asb.summaryIdealValueTv
        tmbTv = asb.summaryCaloricValueTv
        waterTv = asb.summaryWaterTv

        returnBt = asb.summaryReturnBt
        submitBt = asb.summarySubmitBt

        val name = intent.getStringExtra("EXTRA_NAME")
        val age = intent.getIntExtra("EXTRA_AGE", 0)
        val height = intent.getDoubleExtra("EXTRA_HEIGHT", 0.0)
        val weight = intent.getDoubleExtra("EXTRA_WEIGHT", 0.0)
        val sex = intent.getStringExtra("EXTRA_SEX")
        val activityLevel = intent.getStringExtra("EXTRA_ACTIVITY_LEVEL")
        val imc = intent.getDoubleExtra("EXTRA_IMC", 0.0)
        val category = intent.getStringExtra("EXTRA_CATEGORY")
        val tmb = intent.getDoubleExtra("EXTRA_TMB", 0.0)
        val ideal = intent.getDoubleExtra("EXTRA_IDEAL", 0.0)

        nameTv.text = name
        imcTv.text = String.format(Locale.US, "%.2f", imc)
        categoryTv.text = category
        idealTv.text = String.format(Locale.US, "%.1f", ideal)
        tmbTv.text = String.format(Locale.US, "%.2f", tmb)

        val water = calculateWater(weight)
        waterTv.text = buildString {
            append("Ingerir ")
            append(water)
            append("ml ")
            append("de água por dia.")
        }

        returnBt.setOnClickListener { finish() }
        submitBt.setOnClickListener {
            saveToDatabase(
                name ?: "",
                age,
                height,
                weight,
                sex ?: "",
                activityLevel ?: "",
                imc,
                category ?: "",
                tmb,
                ideal
            )

            val intentM = Intent(this, MainActivity::class.java)
            intentM.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intentM)
            finish()
        }
    }
}