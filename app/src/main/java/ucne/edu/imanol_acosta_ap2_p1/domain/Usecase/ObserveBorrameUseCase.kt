package ucne.edu.imanol_acosta_ap2_p1.domain.Usecase

import ucne.edu.imanol_acosta_ap2_p1.domain.Model.Borrame
import ucne.edu.imanol_acosta_ap2_p1.domain.Repository.BorrameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveBorrameUseCase @Inject constructor(
    private val repository: BorrameRepository
) {
    operator fun invoke(): Flow<List<Borrame>> {
        return repository.observeBorrame()
    }
}
