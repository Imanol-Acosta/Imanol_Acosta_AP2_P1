package ucne.edu.imanol_acosta_ap2_p1.data.local.dao
import ucne.edu.imanol_acosta_ap2_p1.data.local.entity.BorrameEntity
import androidx.room.Dao
import androidx.room.Upsert


@Dao
interface BorrameDao {
    @Upsert
    suspend fun upsertBorrame(borrame: BorrameEntity)



}