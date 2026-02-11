package ucne.edu.imanol_acosta_ap2_p1.domain.Repository

import ucne.edu.imanol_acosta_ap2_p1.domain.Model.Borrame
import kotlinx.coroutines.flow.Flow

interface BorrameRepository {
    fun observeBorrame(): Flow<List<Borrame>>
    suspend fun getBorrame(id: Int): Borrame?
    suspend fun upsert(borrame: Borrame): Int
    suspend fun delete(id: Int)
    suspend fun getByNombre(nombre: String): List<Borrame>
}
