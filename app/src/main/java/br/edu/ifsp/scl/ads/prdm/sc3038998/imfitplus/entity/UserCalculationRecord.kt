package br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calculation_records")
data class UserCalculationRecord(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    val timestamp: Long = System.currentTimeMillis(),
    val name: String,
    val age: Int,
    val sex: String,
    val height: Double,
    val weight: Double,
    val activityLevel: String,
    val imc: Double,
    val imcCategory: String,
    val tmb: Double,
    val idealWeight: Double
)