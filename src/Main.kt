fun main() {
    do {
        val tableroJuego = Tablero()
        val nombreJugador1 = pedirNombre("Jugador 1")
        val nombreJugador2 = pedirNombre("Jugador 2")
        val jugador1 = Jugador(nombreJugador1, 'X')
        val jugador2 = Jugador(nombreJugador2, 'O')
        val resultadoPartida = Juego(tableroJuego, arrayOf(jugador1, jugador2)).jugar()
        println("Fin de la partida")
        println("Resultado: ${resultadoPartida}")
        val otraPartida = preguntarOtraPartida()
    } while (otraPartida)
}

fun pedirNombre(numeroJugador: String): String {
    var nombre = ""
    while (nombre.isEmpty()) {
        print("Introduce el nombre del $numeroJugador: ")
        nombre = readln()
    }
    return nombre
}

fun preguntarOtraPartida(): Boolean {
    var otra = ""
    while (otra != "si" && otra != "no") {
        println("¿Quieres jugar otra partida? (si o no): ")
        otra = readln().lowercase()
    }
    if (otra != "no") {
        return true
    } else return false
}