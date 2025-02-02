class Juego(val tablero: Tablero, val jugadores: Array<Jugador>) {
    init {
        require(jugadores.size == 2) { "Debe haber exactamente dos jugadores." }
    }

    var turno: Int = 1
    var resultadoPartida: String? = null

    fun jugar(): String? {
        println("Comienza la partida...")
        do {
            jugarTurno()
        } while (!finPartida() && turno <= 9)
        return resultadoPartida
    }

    fun jugarTurno() {
        val jugadorTurno = if (turno % 2 == 1) jugadores[0] else jugadores[1]
        println("Turno $turno --- turno de ${jugadorTurno.nombre}")
        tablero.mostrarTablero()

        var movimientos: Array<Int>
        do {
            movimientos = pedirMovimiento()
        } while (!tablero.validarMovimiento(movimientos[0], movimientos[1]))

        tablero.colocarSimbolo(movimientos[0], movimientos[1], jugadorTurno.signo)
        turno++
    }

    private fun comprobarColumnas(): Int {
        for (i in tablero.tabla.indices) {
            if (tablero.tabla[0][i] != '-' &&
                tablero.tabla[0][i] == tablero.tabla[1][i] &&
                tablero.tabla[1][i] == tablero.tabla[2][i]) {

                return if (tablero.tabla[0][i] == 'X') 1 else 2
            }
        }
        return 0
    }


    private fun comprobarFilas(): Int {
        for (fila in tablero.tabla) {
            if (fila[0] != '-' && fila[0] == fila[1] && fila[1] == fila[2]) {
                return if (fila[0] == 'X') 1 else 2
            }
        }
        return 0
    }

    private fun comprobarDiagonales(): Int {
        if (tablero.tabla[0][0] != '-' &&
            tablero.tabla[0][0] == tablero.tabla[1][1] &&
            tablero.tabla[1][1] == tablero.tabla[2][2]) {

            return if (tablero.tabla[0][0] == 'X') 1 else 2
        }

        if (tablero.tabla[0][2] != '-' &&
            tablero.tabla[0][2] == tablero.tabla[1][1] &&
            tablero.tabla[1][1] == tablero.tabla[2][0]) {

            return if (tablero.tabla[0][2] == 'X') 1 else 2
        }

        return 0
    }


    fun finPartida(): Boolean {
        val resultado = arrayOf(comprobarFilas(), comprobarColumnas(), comprobarDiagonales())
        for (i in resultado) {
            if (i != 0) {
                resultadoPartida = "¡Gana ${jugadores[i - 1].nombre}!"
                return true
            }
        }
        if (turno > 9) {
            resultadoPartida = "¡Empate!"
            return true
        }
        return false
    }

    fun pedirMovimiento(): Array<Int> {
        var fila: Int?
        var columna: Int?
        do {
            println("Introduce la fila (1-3) para colocar símbolo: ")
            fila = readLine()?.toIntOrNull()
        } while (fila == null || fila !in 1..3)

        do {
            println("Introduce la columna (1-3) para colocar símbolo: ")
            columna = readLine()?.toIntOrNull()
        } while (columna == null || columna !in 1..3)

        return arrayOf(fila - 1, columna - 1)
    }
}
