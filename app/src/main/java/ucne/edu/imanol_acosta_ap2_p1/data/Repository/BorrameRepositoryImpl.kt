package ucne.edu.imanol_acosta_ap2_p1.data.Repository

import kotlinx.coroutines.flow.Flow
import ucne.edu.imanol_acosta_ap2_p1.domain.Repository.BorrameRepository
import ucne.edu.imanol_acosta_ap2_p1.data.local.dao.BorrameDao
import ucne.edu.imanol_acosta_ap2_p1.domain.Model.Borrame

import javax.inject.Inject
class BorrameRepositoryImpl @Inject constructor(
    private val borrameDao: BorrameDao
) : BorrameRepository {
    override fun observeBorrame(): Flow<List<Borrame>> {

        return TODO("")
    }

    override suspend fun getBorrame(id: Int): Borrame? {

        return TODO("")
    }

    override suspend fun upsert(borrame: Borrame): Int {

        return TODO("")
    }

    override suspend fun delete(id: Int) {

    }

    override suspend fun getByNombre(nombre: String): List<Borrame> {
        return TODO("Provide the return value")
    }


}
