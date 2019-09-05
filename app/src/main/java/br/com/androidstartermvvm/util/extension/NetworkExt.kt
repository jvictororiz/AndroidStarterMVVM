package br.com.bb.oewallet.extension

import br.com.androidstartermvvm.util.entities.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import java.net.ConnectException

/**
 * Executa uma [retrofit2.Call] dentro de um contexto de uma coroutine.
 *
 * @return [Result] com objeto de resposta da requisição ou uma mensagem de erro.
 */
suspend fun <T> Call<T>.backgroundCall(dispatcher: CoroutineDispatcher): Result<T?> {
    return withContext(context = dispatcher) {
        try {
            val response = this@backgroundCall.execute()
            if (response.isSuccessful) {
                Result.success(response.body(), response.code())
            } else {
                Result.error(response.headers().get("ERROR"))
            }
        } catch (e: Exception) {
            if (e is ConnectException || e is java.net.UnknownHostException) {
                Result.error<T?>(ConnectException("Seu dispositivo está sem internet."))
            } else {
                Result.error<T?>(e)
            }
        }
    }
}

/**
 * Facilita chamadas sem corpo na resposta, verificando apenas se o chamada retornou uma resposta
 * com sucesso.
 */
suspend fun Call<ResponseBody>.checkCall(dispatcher: CoroutineDispatcher): Result<Boolean> {
    return withContext(context = dispatcher) {
        try {
            val response = this@checkCall.execute()
            if (response.isSuccessful) {
                Result.success(true, response.code())
            } else {
                Result.error("Response wasn't successful (SC = ${response.raw().code()})")
            }
        } catch (e: Exception) {
            Result.error<Boolean>(e)
        }
    }
}
