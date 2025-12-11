package br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.persistence

import android.content.Context
import androidx.activity.contextaware.ContextAware
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.dao.UserCalculationRecordDao
import br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.dao.UserDao
import br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.entity.User
import br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.entity.UserCalculationRecord

@Database(
    entities = [User::class, UserCalculationRecord::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun userCalculationRecordDao(): UserCalculationRecordDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }

}