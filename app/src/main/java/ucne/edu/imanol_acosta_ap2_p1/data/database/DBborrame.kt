package ucne.edu.imanol_acosta_ap2_p1.data.database
import ucne.edu.imanol_acosta_ap2_p1.data.local.entity.BorrameEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BorrameEntity::class], version = 1)
abstract class BorrameDatabase : RoomDatabase()