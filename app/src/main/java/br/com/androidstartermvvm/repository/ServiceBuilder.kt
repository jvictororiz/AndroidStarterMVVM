package br.com.androidstartermvvm.repository

import br.com.androidstartermvvm.model.service.RespostaService
import br.com.androidstartermvvm.model.service.http.BackendInterceptor
import br.com.androidstartermvvm.model.service.http.RetrofitClient
import okhttp3.Interceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.*
import kotlin.reflect.KClass

open class ServiceBuilder {
    companion object {
        private val interceptors: List<Interceptor> = listOf<Interceptor>(BackendInterceptor())
        private val converterFactories: Array<Converter.Factory>? = null
        private val retrofit by lazy<Retrofit> { retrofitBuilder().build() }
        private val apiUrl: String = ""
        private fun retrofitBuilder(): Retrofit.Builder {
            return Retrofit.Builder()
                .baseUrl(apiUrl)
                .client(RetrofitClient.retrofitReference)
                .also { builder ->
                    interceptors.forEach {
                        RetrofitClient.retrofitReference.interceptors().add(it)
                    }
                    converterFactories?.forEach {
                        builder.addConverterFactory(it)
                    }
                }
        }

        fun create(java: Class<RespostaService>): RespostaService {
            return retrofit.create(java)
        }
    }
}
