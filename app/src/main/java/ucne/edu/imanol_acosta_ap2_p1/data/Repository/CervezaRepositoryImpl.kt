package ucne.edu.imanol_acosta_ap2_p1.data.Repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ucne.edu.imanol_acosta_ap2_p1.data.Mapper.toDomain
import ucne.edu.imanol_acosta_ap2_p1.data.Mapper.toEntity
import ucne.edu.imanol_acosta_ap2_p1.data.local.dao.CervezaDao
import ucne.edu.imanol_acosta_ap2_p1.domain.Model.Cerveza
import ucne.edu.imanol_acosta_ap2_p1.domain.Repository.CervezaRepository
import javax.inject.Inject

class CervezaRepositoryImpl @Inject constructor(
    private val cervezaDao: CervezaDao
) : CervezaRepository {
    override fun observeCerveza(): Flow<List<Cerveza>> {
        return cervezaDao.observerAll().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getCerveza(id: Int): Cerveza? {
        return cervezaDao.getById(id)?.toDomain()
    }

    override suspend fun upsert(cerveza: Cerveza): Int {
        val entity = cerveza.toEntity()
        val result = cervezaDao.upsert(entity)
        return if (cerveza.idCerveza == 0) result.toInt() else cerveza.idCerveza
    }

    override suspend fun delete(id: Int) {
        cervezaDao.deleteById(id)
    }

    override suspend fun getCervezasByNombre(nombre: String): List<Cerveza> {
        return cervezaDao.getCervezasByNombre(nombre).map { it.toDomain() }
    }
}
