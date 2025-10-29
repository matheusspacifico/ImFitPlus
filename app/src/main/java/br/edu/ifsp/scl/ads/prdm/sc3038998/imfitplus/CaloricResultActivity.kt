package br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.databinding.ActivityCaloricResultBinding

class CaloricResultActivity : AppCompatActivity() {

    private val acrb: ActivityCaloricResultBinding by lazy {
        ActivityCaloricResultBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(acrb.root)


    }
}