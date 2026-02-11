package ucne.edu.imanol_acosta_ap2_p1.domain.Usecase

import ucne.edu.imanol_acosta_ap2_p1.domain.Model.Borrame
import ucne.edu.imanol_acosta_ap2_p1.domain.Repository.BorrameRepository
import javax.inject.Inject

class GetBorrameUseCase @Inject constructor(
    private val repository: BorrameRepository
) {
    suspend operator fun invoke(id: Int): Borrame? {
        if (id <= 0) throw IllegalArgumentException("El id debe ser mayor a 0")
        return repository.getBorrame(id)
    }
}
