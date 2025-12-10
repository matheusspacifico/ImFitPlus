package br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.dao

import androidx.room.Insert
import androidx.room.Query
import br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.entity.UserCalculationRecord

interface UserCalculationRecordDao {

    @Query("SELECT * FROM user_calculation_records")
    suspend fun getAllUserCalculationRecords(): List<UserCalculationRecord>

    @Query("SELECT * FROM user_calculation_records WHERE id = :recordId")
    suspend fun getUserCalculationRecordById(recordId: Long): UserCalculationRecord?

    @Insert
    suspend fun insertUserCalculationRecord(record: UserCalculationRecord): Long
}