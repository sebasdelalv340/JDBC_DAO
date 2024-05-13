import java.util.*

interface IBookDAO {
    fun create(libro: Libro): Libro?
    fun getAll(): List<Libro>?
    fun getById(id: UUID): Libro?
    fun update(libro: Libro): Libro?
    fun delete(id: UUID): Boolean
}