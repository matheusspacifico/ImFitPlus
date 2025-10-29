package br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.databinding.ActivityImcResultBinding

class ImcResultActivity : AppCompatActivity() {

    private val airb: ActivityImcResultBinding by lazy {
        ActivityImcResultBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(airb.root)

    }
}