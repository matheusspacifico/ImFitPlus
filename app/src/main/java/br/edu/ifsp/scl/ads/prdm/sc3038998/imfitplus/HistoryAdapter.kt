package br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.entity.UserCalculationRecord
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var records = listOf<UserCalculationRecord>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)

        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: HistoryViewHolder,
        position: Int
    ) {
        return holder.bind(records[position])
    }

    override fun getItemCount(): Int {
        return records.size
    }

    fun submitList(newRecords: List<UserCalculationRecord>) {
        records = newRecords
        notifyDataSetChanged()
    }

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTv: TextView = itemView.findViewById(R.id.itemNameTv)
        private val dateTv: TextView = itemView.findViewById(R.id.itemDateTv)
        private val imcTv: TextView = itemView.findViewById(R.id.itemImcTv)
        private val categoryTv: TextView = itemView.findViewById(R.id.itemCategoryTv)
        private val tmbTv: TextView = itemView.findViewById(R.id.itemTmbTv)
        private val idealTv: TextView = itemView.findViewById(R.id.itemIdealTv)
        private val detailsTv: TextView = itemView.findViewById(R.id.itemDetailsTv)
        private val leveTv: TextView = itemView.findViewById(R.id.itemLeveTv)
        private val queimaTv: TextView = itemView.findViewById(R.id.itemQueimaTv)
        private val aerobicaTv: TextView = itemView.findViewById(R.id.itemAerobicaTv)
        private val anaerobicaTv: TextView = itemView.findViewById(R.id.itemAnaerobicaTv)

        fun bind(record: UserCalculationRecord) {
            nameTv.text = record.name

            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US)
            val date = Date(record.timestamp)

            dateTv.text = dateFormat.format(date)

            imcTv.text = String.format(Locale.US, "IMC: %.2f", record.imc)
            categoryTv.text = record.imcCategory

            tmbTv.text = String.format(Locale.US, "TMB: %.2f kcal", record.tmb)
            idealTv.text = String.format(Locale.US, "Peso Ideal: %.1f kg", record.idealWeight)

            leveTv.text = record.leve
            queimaTv.text = record.queima
            aerobicaTv.text = record.aerobica
            anaerobicaTv.text = record.anerobica

            detailsTv.text = buildString {
                append(record.birthdate)
                append(" • ")
                append(record.age)
                append(" anos • ")
                append(record.sex)
                append(" • ")
                append(String.format(Locale.US, "%.2f", record.weight))
                append(" kg • ")
                append(String.format(Locale.US, "%.2f", record.height))
                append(" m")
            }
        }

    }
}