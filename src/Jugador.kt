class Jugador(val nombre: String, val signo: Char) {
    init {
        require (signo == 'O' || signo == 'X')
    }
}