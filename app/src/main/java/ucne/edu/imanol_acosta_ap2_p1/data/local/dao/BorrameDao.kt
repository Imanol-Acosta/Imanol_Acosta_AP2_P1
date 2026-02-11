package ucne.edu.imanol_acosta_ap2_p1.data.local.dao

import ucne.edu.imanol_acosta_ap2_p1.data.local.entity.BorrameEntity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BorrameDao {
    @Query("SELECT * FROM Cerveza ORDER BY CervezaId DESC")
    fun observeAll(): Flow<List<BorrameEntity>>

    @Query("SELECT * FROM Cerveza WHERE CervezaId = :id")
    suspend fun getById(id: Int): BorrameEntity?

    @Upsert
    suspend fun upsert(borrame: BorrameEntity): Long

    @Delete
    suspend fun delete(borrame: BorrameEntity)

}