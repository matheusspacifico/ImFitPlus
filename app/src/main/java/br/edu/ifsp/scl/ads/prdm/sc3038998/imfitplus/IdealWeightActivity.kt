package br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.databinding.ActivityIdealWeightBinding
import java.util.Locale
import kotlin.math.abs
import kotlin.math.pow

class IdealWeightActivity : AppCompatActivity() {

    private val aiwb: ActivityIdealWeightBinding by lazy {
        ActivityIdealWeightBinding.inflate(layoutInflater)
    }

    private lateinit var resultTv: TextView
    private lateinit var diffTv: TextView
    private lateinit var recommendationCb: CheckBox
    private lateinit var returnBt: Button
    private lateinit var submitBt: Button

    private fun calculateIdealWeight(height: Double): Double {
        return 22 * height.pow(2)
    }

    private fun getDiff(weight: Double, idealWeight: Double): Double {
        return weight - idealWeight
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(aiwb.root)

        resultTv = aiwb.idealWeightValueTv
        diffTv = aiwb.idealDiffValueTv
        recommendationCb = aiwb.idealRecommendationsCb

        returnBt = aiwb.idealReturnBt
        submitBt = aiwb.idealFinishBt

        val name = intent.getStringExtra("EXTRA_NAME")
        val age = intent.getIntExtra("EXTRA_AGE", 0)
        val height = intent.getDoubleExtra("EXTRA_HEIGHT", 0.0)
        val weight = intent.getDoubleExtra("EXTRA_WEIGHT", 0.0)
        val sex = intent.getStringExtra("EXTRA_SEX")
        val activityLevel = intent.getStringExtra("EXTRA_ACTIVITY_LEVEL")
        val imc = intent.getDoubleExtra("EXTRA_IMC", 0.0)
        val category = intent.getStringExtra("EXTRA_CATEGORY")
        val tmb = intent.getDoubleExtra("EXTRA_TMB", 0.0)

        val idealWeight = calculateIdealWeight(height)
        val diffWeight = getDiff(weight, idealWeight)

        resultTv.text = String.format(Locale.US, "%.1f", idealWeight)

        if (abs(diffWeight) < 1) {
            diffTv.text = getString(R.string.ideal_diff_normal)
        } else if (diffWeight > 0) {
            diffTv.text = getString(R.string.ideal_diff_lose, abs(diffWeight))
        } else {
            diffTv.text = getString(R.string.ideal_diff_gain, abs(diffWeight))
        }

        recommendationCb.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                aiwb.idealRecommendationsLayout.visibility = View.VISIBLE
            } else {
                aiwb.idealRecommendationsLayout.visibility = View.GONE
            }
        }

        returnBt.setOnClickListener { finish() }
        submitBt.setOnClickListener {
//            val intentM = Intent(this, MainActivity::class.java)
//            intentM.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
//            startActivity(intentM)
//            finish()
            val intentS = Intent(this, SummaryActivity::class.java)
            intentS.putExtra("EXTRA_NAME", name)
            intentS.putExtra("EXTRA_AGE", age)
            intentS.putExtra("EXTRA_HEIGHT", height)
            intentS.putExtra("EXTRA_WEIGHT", weight)
            intentS.putExtra("EXTRA_SEX", sex)
            intentS.putExtra("EXTRA_ACTIVITY_LEVEL", activityLevel)
            intentS.putExtra("EXTRA_IMC", imc)
            intentS.putExtra("EXTRA_CATEGORY", category)
            intentS.putExtra("EXTRA_TMB", tmb)
            intentS.putExtra("EXTRA_IDEAL", idealWeight)

            startActivity(intentS)
        }
    }
}