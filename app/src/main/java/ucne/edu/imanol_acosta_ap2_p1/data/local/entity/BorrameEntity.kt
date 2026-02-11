package ucne.edu.imanol_acosta_ap2_p1.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Borrame")
data class BorrameEntity(
    @PrimaryKey(autoGenerate = true)
    val borrameId: Int = 0,
    val nombre: String = "",
    val descripcion: String = "",
    val valor: Int = 0
)
