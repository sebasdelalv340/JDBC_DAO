package output

import Libro

interface IOutputInfo {
    fun showMessage(message: String, lineBreak: Boolean = true)
    fun show(userList: List<Libro>, message: String = "All users:")
}