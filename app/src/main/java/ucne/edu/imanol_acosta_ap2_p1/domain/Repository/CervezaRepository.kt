package ucne.edu.imanol_acosta_ap2_p1.domain.Repository

import kotlinx.coroutines.flow.Flow
import ucne.edu.imanol_acosta_ap2_p1.domain.Model.Cerveza

interface CervezaRepository {
    fun observeCerveza(): Flow<List<Cerveza>>
    suspend fun getCerveza(id: Int): Cerveza?
    suspend fun upsert(cerveza: Cerveza): Int
    suspend fun delete(id: Int)
    suspend fun getCervezasByNombre(nombre: String): List<Cerveza>
}
