package org.bedu.bonappetit.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.bedu.bonappetit.R
import org.bedu.bonappetit.data.repository.UserRepository
import org.bedu.bonappetit.util.NetworkConnectionError


//Como es un view model debemos recibir el repository
class LoginViewModel( val repository: UserRepository): ViewModel() {

    val user = MutableLiveData("") //Se van a conectar a tavés de data binding para actualizarse en automático los valores
    val password = MutableLiveData("")

    private val _token = MutableLiveData<String>() //El toquen es el de la respuesta
    val token get()= _token

    private val _error = MutableLiveData<Int>() //Error que se muestre en Snack bar por el error
    val error: LiveData<Int?> get() = _error

    fun login(){
        if(user.value!!.isEmpty() || password.value!!.isEmpty()){
            _error.value = R.string.empty_field
            return
        }
        // con esto vamos a llevar este método con la courutina
        //Colocamos una courutina
        viewModelScope.launch {
            //Vamos a gestionar los errores con esto
            try {
                //vamos a llamar la respuesta
                val response = repository.loginUser(user.value!!, password.value!!)
                _token.value = response?.token
            }catch (error: NetworkConnectionError){
                //Gestionamos si hay un error de conexión
                _error.value = R.string.network_error
            }
        }
    }

}