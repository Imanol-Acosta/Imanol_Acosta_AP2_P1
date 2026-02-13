package ucne.edu.imanol_acosta_ap2_p1.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import ucne.edu.imanol_acosta_ap2_p1.data.local.entity.CervezaEntity

@Dao
interface CervezaDao {
    @Query("SELECT * FROM cervezas ORDER BY idCerveza DESC")
    fun observerAll(): Flow<List<CervezaEntity>>

    @Query("SELECT * FROM cervezas WHERE idCerveza = :id")
    suspend fun getById(id: Int): CervezaEntity?

    @Upsert
    suspend fun upsert(cerveza: CervezaEntity): Long

    @Delete
    suspend fun delete(cerveza: CervezaEntity)

    @Query("DELETE FROM cervezas WHERE idCerveza = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM cervezas WHERE nombre = :nombre")
    suspend fun getCervezasByNombre(nombre: String): List<CervezaEntity>
}
