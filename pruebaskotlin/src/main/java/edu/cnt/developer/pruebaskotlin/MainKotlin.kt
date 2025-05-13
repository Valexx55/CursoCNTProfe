package edu.cnt.developer.pruebaskotlin

fun main ()
{
    println("Hola")
    val n = (0..100).random()
    println("Aleaotorio = $n")

    var usuario1: Usuario = Usuario("Vale", 42)
    var usuario2: Usuario = Usuario("Pepa", 18)
    var usuario3: Usuario = Usuario("José", 36)
    var usuario4: Usuario = Usuario("Juani", 12)
    var usuario5: Usuario = Usuario("Manolo", 81)

    var listaUsuarios = listOf(usuario5, usuario4, usuario3, usuario2, usuario1)

    listaUsuarios.forEach({usario -> println(usario) })
    listaUsuarios.forEach{usario -> println(usario.nombre) }
    var listaMenores30 =  listaUsuarios.filter { u -> u.edad <= 30 }
    listaMenores30.forEach{u -> println(u.nombre+ " " + u.edad ) }





}