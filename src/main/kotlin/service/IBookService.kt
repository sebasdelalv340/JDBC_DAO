import java.util.*

interface BookService {
    fun create(libro: Libro): Libro?
    fun getAll(): List<Libro>?
    fun getById(id: UUID): Libro?
    fun update(libro: Libro): Libro?
    fun delete(id: UUID): Boolean
}