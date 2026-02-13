package ucne.edu.imanol_acosta_ap2_p1.domain.Usecase

import ucne.edu.imanol_acosta_ap2_p1.domain.Model.Cerveza
import ucne.edu.imanol_acosta_ap2_p1.domain.Repository.CervezaRepository
import javax.inject.Inject

class UpsertCervezaUseCase @Inject constructor(
    private val repository: CervezaRepository
) {
    suspend operator fun invoke(cerveza: Cerveza): Result<Int> {
        if (cerveza.nombre.isBlank()) {
            return Result.failure(IllegalArgumentException("El nombre no puede estar vacío"))
        }
        if (cerveza.puntuacion < 0) {
            return Result.failure(IllegalArgumentException("La puntuación debe ser positiva"))
        }
        
        return runCatching {
            repository.upsert(cerveza)
        }
    }
}
