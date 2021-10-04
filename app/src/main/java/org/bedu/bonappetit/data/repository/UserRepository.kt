package org.bedu.bonappetit.data.repository

import org.bedu.bonappetit.api.LoginService
import org.bedu.bonappetit.data.models.LoginResponse
import org.bedu.bonappetit.util.NetworkConnectionError


class UserRepository(val loginService: LoginService) {

    //Recuperamos la información del servidor remoto

    suspend fun loginUser(user:String,password:String): LoginResponse?{
        //En lugar de callback vamos a usar la courutina
        //Con esto igual gestionamos el manejo de errores por si no es exitosa o si no hubo una respuesta
        try {
            val result = loginService.login(user,password) //Con este result podemos gestionar igual errores
            if(!result.isSuccessful){
                throw NetworkConnectionError("Usuarios no reconocidos",null)
            }
            //Aquí se pueden hacer algo para que se ejecute aquí por la llamada correcta
            return result.body()
        }catch (cause:Throwable){
            //Asumimos en este caso que es por un error de conexión
            throw NetworkConnectionError("Hubo un error al llamar el servicio",cause)
        }
    }

}