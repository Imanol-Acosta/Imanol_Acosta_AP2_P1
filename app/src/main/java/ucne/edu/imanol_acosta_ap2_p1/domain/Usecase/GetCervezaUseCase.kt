package ucne.edu.imanol_acosta_ap2_p1.domain.Usecase

import ucne.edu.imanol_acosta_ap2_p1.domain.Model.Cerveza
import ucne.edu.imanol_acosta_ap2_p1.domain.Repository.CervezaRepository
import javax.inject.Inject

class GetCervezaUseCase @Inject constructor(
    private val repository: CervezaRepository
) {
    suspend operator fun invoke(id: Int): Cerveza? {
        if (id <= 0) throw IllegalArgumentException("El id debe ser mayor a 0")
        return repository.getCerveza(id)
    }
}
