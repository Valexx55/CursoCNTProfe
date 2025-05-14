package edu.cnt.developer.profe.productos

import retrofit2.http.GET

interface ProductoService {

    @GET("miseon920/json-api/products")
    suspend fun obtenerProductos():ListadoProductos
}