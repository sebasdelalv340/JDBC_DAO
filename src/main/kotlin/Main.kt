import db_connection.DataSourceFactory
import output.Console

fun main() {
    val console = Console()
    // Creamos la instancia de la base de datos
    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.HIKARI)

    // Creamos la instancia de UserDAO
    val userDao = BookDAOH2(dataSource, console)

    // Creamos la instancia de BookService
    val bookService = BookServiceImpl(userDao)

    // Creamos un nuevo libro
    val newBook = Libro(titulo = "Kotlin en 2025", autor = "John Doe", anioPublicacion = 2025)
    var createdBook = bookService.create(newBook)
    console.showMessage("Created book: ${createdBook ?: "error"}")

    // Obtenemos un libro por su ID
    val foundBook = createdBook?.let { bookService.getById(it.id) }
    console.showMessage("Found user: ${foundBook ?: "error"}")

    // Actualizamos el libro
    val updatedBook = foundBook?.copy(autor = "Jane Doe")
    val savedBook = updatedBook?.let { bookService.update(it) }
    console.showMessage("Updated book: ${savedBook ?: "error"}")

    val otherBook = Libro(titulo = "Programación para principiantes", autor = "Eduardo Fernandez", anioPublicacion = 2024)
    createdBook = bookService.create( otherBook)
    console.showMessage("Created book: ${createdBook?: "error"}")


    // Obtenemos todos los usuarios
    var allBooks = bookService.getAll()
    if (allBooks != null) {
        console.show(allBooks,"All book:")
    }

    // Eliminamos el usuario
    if (savedBook != null) {
        if (bookService.delete(savedBook.id)) {
            console.showMessage("Book deleted")
        } else {
            null
        }
    }


    // Obtenemos todos los usuarios
    allBooks = bookService.getAll()
    if (allBooks != null) {
        console.show(allBooks,"All user:")
    }

    // Eliminamos el usuario
    bookService.delete(otherBook.id)
    console.showMessage("User deleted")
}