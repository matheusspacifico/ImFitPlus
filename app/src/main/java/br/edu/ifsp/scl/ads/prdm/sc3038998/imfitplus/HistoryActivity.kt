package br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.databinding.ActivityHistoryBinding
import br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.persistence.AppDatabase
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {

    private val ahb: ActivityHistoryBinding by lazy {
        ActivityHistoryBinding.inflate(layoutInflater)
    }

    private lateinit var historyRv: RecyclerView
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var returnBt: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ahb.root)

        historyRv = ahb.historyRv
        returnBt = ahb.historyReturnBt

        historyAdapter = HistoryAdapter()
        historyRv.adapter = historyAdapter

        returnBt.setOnClickListener { finish() }

        loadHistory()
    }

    private fun loadHistory() {
        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(applicationContext)
            val userCalculationRecordDao = db.userCalculationRecordDao()

            val records = userCalculationRecordDao.getAllUserCalculationRecords()
            historyAdapter.submitList(records)
        }
    }
}