package org.bedu.bonappetit.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {

    //Estructura básica para Networking
    private const val STORE_URL = "https://fakestoreapi.com" //eve.holt@reqres.in
    private const val LOGIN_URL = "https://reqres.in/api/"

    //Vamos a colocar interceptores o colocar tiempos máximos de respuesta y ver las respuestas en el logcat
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(LOGIN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    // Creamos nuestra instancia de retrofit
    // Se crea con by lazy cuando se haga la primera llamada al elemento
    val loginService: LoginService by lazy { retrofit.create(LoginService::class.java) }

}