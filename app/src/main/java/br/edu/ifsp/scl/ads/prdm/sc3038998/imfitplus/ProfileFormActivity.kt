package br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
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
    private lateinit var activityLevelRg: RadioGroup
    private lateinit var returnBt: Button
    private lateinit var submitBt: Button

    private fun calculateImc(weight: Double, height: Double): Double {
        if (height == 0.0) {
            return 0.0
        }
        return weight * height.pow(2)
    }

    private fun validateFields(): Boolean {
        if (nameEt.text.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (ageEt.text.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (heightEt.text.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (weightEt.text.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return false
        }

        val age = ageEt.text.toString().toIntOrNull()
        if (age == null || age <= 0) {
            Toast.makeText(this, "Insira uma idade válida!", Toast.LENGTH_SHORT).show()
            return false
        }

        val height = heightEt.text.toString().toDoubleOrNull()
        if (height == null || height <= 0.0) {
            Toast.makeText(this, "Insira uma altura válida!", Toast.LENGTH_SHORT).show()
            return false
        }

        val weight = weightEt.text.toString().toDoubleOrNull()
        if (weight == null || weight <= 0.0) {
            Toast.makeText(this, "Insira um peso válida!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (activityLevelRg.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Selecione um nível de atividade física!", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun submitForm() {
        if (!validateFields()) return

        val name = nameEt.text.toString()
        val age = ageEt.text.toString().toInt()
        val height = heightEt.text.toString().toDouble()
        val weight = weightEt.text.toString().toDouble()
        val sex = sexSp.selectedItem.toString()
        val activityLevel = activityLevelRg.checkedRadioButtonId
        val imc = calculateImc(weight, height)

        val intent = Intent(this, ImcResultActivity::class.java)
        intent.putExtra("EXTRA_NAME", name)
        intent.putExtra("EXTRA_AGE", age)
        intent.putExtra("EXTRA_HEIGHT", height)
        intent.putExtra("EXTRA_WEIGHT", weight)
        intent.putExtra("EXTRA_SEX", sex)
        intent.putExtra("EXTRA_ACTIVITY_LEVEL", activityLevel)
        intent.putExtra("EXTRA_IMC", imc)

        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apfb.root)

        nameEt = apfb.formNameEt
        ageEt = apfb.formAgeEt
        heightEt = apfb.formHeightEt
        weightEt = apfb.formWeightEt

        sexSp = apfb.formSexEt

        activityLevelRg = apfb.formActivityLevelRg

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

        returnBt.setOnClickListener { finish() }
        submitBt.setOnClickListener { submitForm() }
    }
}