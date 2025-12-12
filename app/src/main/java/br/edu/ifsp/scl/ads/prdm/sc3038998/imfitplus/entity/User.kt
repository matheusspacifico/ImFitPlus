package br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "users")
data class User(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val age: Int,
    val birthdate: String,
    val height: Double,
    val weight: Double,
    val activityLevel: String
)