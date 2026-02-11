package ucne.edu.imanol_acosta_ap2_p1.data.database

import ucne.edu.imanol_acosta_ap2_p1.data.local.entity.BorrameEntity
import ucne.edu.imanol_acosta_ap2_p1.data.local.dao.BorrameDao
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BorrameEntity::class], version = 1, exportSchema = false)
abstract class BorrameDatabase : RoomDatabase() {
    abstract fun borrameDao(): BorrameDao
}