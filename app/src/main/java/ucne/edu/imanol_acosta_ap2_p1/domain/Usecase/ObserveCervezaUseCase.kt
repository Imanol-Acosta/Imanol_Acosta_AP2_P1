package ucne.edu.imanol_acosta_ap2_p1.domain.Usecase

import kotlinx.coroutines.flow.Flow
import ucne.edu.imanol_acosta_ap2_p1.domain.Model.Cerveza
import ucne.edu.imanol_acosta_ap2_p1.domain.Repository.CervezaRepository
import javax.inject.Inject

class ObserveCervezaUseCase @Inject constructor(
    private val repository: CervezaRepository
) {
    operator fun invoke(): Flow<List<Cerveza>> {
        return repository.observeCerveza()
    }
}
