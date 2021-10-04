package org.bedu.bonappetit

import android.app.Application
import org.bedu.bonappetit.api.Api
import org.bedu.bonappetit.data.repository.UserRepository

class ShoppingApplication: Application() {
    //Crearemos una instancia de nuestro servicio

    private val loginService = Api.loginService

    val userRepository by lazy { UserRepository(loginService) }
}