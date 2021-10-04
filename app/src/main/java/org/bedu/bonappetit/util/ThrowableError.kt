package org.bedu.bonappetit.util

class NetworkFailedError(message: String, cause: Throwable?) : Throwable(message, cause)
class NetworkConnectionError(message: String, cause: Throwable?) : Throwable(message, cause)