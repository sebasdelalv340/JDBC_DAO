package output

import Libro

class Console: IOutputInfo {
    override fun showMessage(message: String, lineBreak: Boolean) {
        if (lineBreak) {
            println(message)
        } else {
            print(message)
        }
    }

    override fun show(libroList: List<Libro>, message: String) {
        if (libroList.isNotEmpty()) {
            showMessage(message)
            libroList.forEachIndexed { index, user ->
                showMessage("\t${index + 1}. $user")
            }
        } else {
            showMessage("No users found!")
        }
    }

}