import java.util.*

data class Libro(
    var id: UUID = UUID.randomUUID(),
    var titulo: String,
    var autor: String,
    var anioPublicacion: Int
)