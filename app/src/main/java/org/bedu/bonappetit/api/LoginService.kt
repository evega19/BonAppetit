package org.bedu.bonappetit.api


import org.bedu.bonappetit.data.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.HTTP
import retrofit2.http.POST

interface LoginService {
    //Con esto colocamos couritinas en lugar de callback, requerimos colocar funciones tipo suspend

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ) : Response<LoginResponse> //Ya no usamo Call para no usar callback si no sólo la respuesta


}