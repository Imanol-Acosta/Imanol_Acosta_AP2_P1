package ucne.edu.imanol_acosta_ap2_p1.domain.Usecase

import ucne.edu.imanol_acosta_ap2_p1.domain.Model.Borrame
import ucne.edu.imanol_acosta_ap2_p1.domain.Repository.BorrameRepository
import javax.inject.Inject

class UpsertBorrameUseCase @Inject constructor(
    private val repository: BorrameRepository
) {
    suspend operator fun invoke(borrame: Borrame): Result<Int> {
        if (borrame.nombre.isBlank()) {
            return Result.failure(IllegalArgumentException("El nombre no puede estar vacío"))
        }
        return runCatching {
            repository.upsert(borrame)
        }
    }
}
