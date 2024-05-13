import output.IOutputInfo
import java.sql.SQLException
import java.util.*
import javax.sql.DataSource

class BookDAOH2(
    private val dataSource: DataSource,
    private val console: IOutputInfo
) : IBookDAO {

    override fun create(libro: Libro): Libro? {
        val sql = "INSERT INTO tlibro (id, titulo, autor, anioPublicacion) VALUES (?, ?, ?, ?)"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, libro.id.toString())
                    stmt.setString(2, libro.titulo)
                    stmt.setString(3, libro.autor)
                    stmt.setInt(4, libro.anioPublicacion)
                    val rs = stmt.executeUpdate()
                    if (rs == 1) {
                        libro
                    } else {
                        console.showMessage("*Error* insert query failed! ($rs records inserted)")
                        null
                    }
                }
            }
        } catch (e: SQLException) {
            console.showMessage("*Error* insert query failed! (${e.message})")
            null
        }
    }

    override fun getById(id: UUID): Libro? {
        val sql = "SELECT * FROM tlibro WHERE id = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id.toString())
                    val rs = stmt.executeQuery()
                    if (rs.next()) {
                        Libro(
                            id = UUID.fromString(rs.getString("id")),
                            titulo = rs.getString("titulo"),
                            autor = rs.getString("autor"),
                            anioPublicacion = rs.getInt("anioPublicacion")
                        )
                    } else {
                        null
                    }
                }
            }
        } catch (e: SQLException) {
            console.showMessage("*Error* select query failed! (${e.message})")
            null
        }
    }

    override fun getAll(): List<Libro>? {
        val sql = "SELECT * FROM tlibro"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    val rs = stmt.executeQuery()
                    val users = mutableListOf<Libro>()
                    while (rs.next()) {
                        users.add(
                            Libro(
                                id = UUID.fromString(rs.getString("id")),
                                titulo = rs.getString("titulo"),
                                autor = rs.getString("autor"),
                                anioPublicacion = rs.getInt("anioPublicacion")
                            )
                        )
                    }
                    users
                }
            }
        }catch (e: SQLException) {
            console.showMessage("*Error* select query failed! (${e.message})")
            null
        }
    }

    override fun update(libro: Libro): Libro? {
        val sql = "UPDATE tlibro SET titulo = ?, autor = ?, anioPublicacion = ? WHERE id = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(2, libro.titulo)
                    stmt.setString(3, libro.autor)
                    stmt.setInt(4, libro.anioPublicacion)
                    stmt.setString(3, libro.id.toString())
                    val rs = stmt.executeUpdate()
                    if (rs == 1) {
                        libro
                    } else {
                        console.showMessage("*Error* update query failed! ($rs records inserted)")
                        null
                    }
                }
            }
        } catch (e: SQLException) {
            console.showMessage("*Error* select query failed! (${e.message})")
            null
        }
    }

    override fun delete(id: UUID): Boolean {
        val sql = "DELETE FROM tlibro WHERE id = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id.toString())
                    (stmt.executeUpdate() == 1)
                }
            }
        } catch (e: SQLException) {
            console.showMessage("*Error* delete query failed! (${e.message})")
            false
        }
    }

}