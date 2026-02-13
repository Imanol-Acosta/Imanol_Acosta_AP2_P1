package ucne.edu.imanol_acosta_ap2_p1.data.Mapper

import ucne.edu.imanol_acosta_ap2_p1.data.local.entity.CervezaEntity
import ucne.edu.imanol_acosta_ap2_p1.domain.Model.Cerveza

fun CervezaEntity.toDomain(): Cerveza =
    Cerveza(
        idCerveza = idCerveza,
        nombre = nombre,
        marca = marca,
        puntuacion = puntuacion
    )

fun Cerveza.toEntity(): CervezaEntity =
    CervezaEntity(
        idCerveza = idCerveza,
        nombre = nombre,
        marca = marca,
        puntuacion = puntuacion
    )
