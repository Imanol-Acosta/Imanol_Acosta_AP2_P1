package ucne.edu.imanol_acosta_ap2_p1.domain.Usecase

import ucne.edu.imanol_acosta_ap2_p1.domain.Repository.BorrameRepository
import javax.inject.Inject

class DeleteBorrameUseCase @Inject constructor(
    private val repository: BorrameRepository
) {
    suspend operator fun invoke(id: Int) {
        if (id <= 0) throw IllegalArgumentException("El ID tiene que ser mayor que 0")
        repository.delete(id)
    }
}
