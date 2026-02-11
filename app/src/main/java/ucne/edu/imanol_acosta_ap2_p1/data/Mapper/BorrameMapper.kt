package ucne.edu.imanol_acosta_ap2_p1.data.Mapper

import ucne.edu.imanol_acosta_ap2_p1.domain.Model.Borrame
import ucne.edu.imanol_acosta_ap2_p1.data.local.entity.BorrameEntity

fun BorrameEntity.toDomain(): Borrame =
    Borrame(
        cervezaId = cervezaId,
        nombre = nombre,
        puntuacion = puntuacion,
    )

fun Borrame.toEntity(): BorrameEntity =
    BorrameEntity(
        cervezaId = cervezaId,
        nombre = nombre,
        puntuacion = puntuacion,

    )
