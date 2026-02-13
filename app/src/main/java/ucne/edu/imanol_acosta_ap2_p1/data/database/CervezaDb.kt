package ucne.edu.imanol_acosta_ap2_p1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ucne.edu.imanol_acosta_ap2_p1.data.local.dao.CervezaDao
import ucne.edu.imanol_acosta_ap2_p1.data.local.entity.CervezaEntity

@Database(entities = [CervezaEntity::class], version = 1, exportSchema = false)
abstract class CervezaDb : RoomDatabase() {
    abstract fun cervezaDao(): CervezaDao
}