import java.util.*

class BookServiceImpl(private val bookDao: IBookDAO) : BookService {
    override fun create(libro: Libro): Libro? {
        return bookDao.create(libro)
    }

    override fun getById(id: UUID): Libro? {
        return bookDao.getById(id)
    }

    override fun update(libro: Libro): Libro? {
        return bookDao.update(libro)
    }

    override fun delete(id: UUID): Boolean {
        return if (bookDao.delete(id)) {
            true
        } else {
            false
        }
    }

    override fun getAll(): List<Libro>? {
        return bookDao.getAll()
    }
}