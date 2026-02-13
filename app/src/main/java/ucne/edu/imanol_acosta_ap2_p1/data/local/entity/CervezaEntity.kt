package ucne.edu.imanol_acosta_ap2_p1.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cervezas")
data class CervezaEntity(
    @PrimaryKey(autoGenerate = true)
    val idCerveza: Int = 0,
    val nombre: String,
    val marca: String,
    val puntuacion: Int
)