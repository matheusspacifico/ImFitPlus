package br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.databinding.ActivityProfileFormBinding
import kotlin.math.pow

class ProfileFormActivity : AppCompatActivity() {

    private val apfb: ActivityProfileFormBinding by lazy {
        ActivityProfileFormBinding.inflate(layoutInflater)
    }

    private lateinit var nameEt: EditText
    private lateinit var ageEt: EditText
    private lateinit var heightEt: EditText
    private lateinit var weightEt: EditText
    private lateinit var sexSp: Spinner
    private lateinit var activityRg: RadioGroup
    private lateinit var sedentaryRb: RadioButton
    private lateinit var lightRb: RadioButton
    private lateinit var moderateRb: RadioButton
    private lateinit var intenseRb: RadioButton
    private lateinit var returnBt: Button
    private lateinit var submitBt: Button

    private fun calculateImc(weight: Float, height: Float): Float {
        return weight * height.pow(2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apfb.root)

        nameEt = apfb.formNameEt
        ageEt = apfb.formAgeEt
        heightEt = apfb.formHeightEt
        weightEt = apfb.formWeightEt

        sexSp = apfb.formSexEt

        activityRg = apfb.formActivityLevelRg
        sedentaryRb = apfb.formSedentaryRb
        lightRb = apfb.formLightRb
        moderateRb = apfb.formModerateRb
        intenseRb = apfb.formIntenseRb

        returnBt = apfb.formReturnBt
        submitBt = apfb.formSubmitBt

        ArrayAdapter.createFromResource(
            this,
            R.array.form_sex_sp,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sexSp.adapter = adapter
        }

    }
}