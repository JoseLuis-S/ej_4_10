class Tablero() {
    companion object {
        private const val DIMENSIONES: Int = 3
    }

    val tabla: Array<Array<Char>> = Array(DIMENSIONES) { Array(DIMENSIONES) { '-' } }

    fun mostrarTablero() {
        for (fila in tabla) {
            println(fila.joinToString(" | "))
        }
    }

    fun colocarSimbolo(fila: Int, columna: Int, simbolo: Char) {
        tabla[fila][columna] = simbolo
    }

    fun validarMovimiento(fila: Int, columna: Int): Boolean {
        try {
            if (tabla[fila][columna] == '-') {
                return true

            } else throw Exception()

        } catch (e: Exception) {
            println("La posicion no es valida.")
            return false
        }
    }

}