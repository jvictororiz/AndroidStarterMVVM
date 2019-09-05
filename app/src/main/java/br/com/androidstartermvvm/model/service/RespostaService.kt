package br.com.androidstartermvvm.model.service

import br.com.androidstartermvvm.model.entities.Resposta
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

interface RespostaService {
    @POST("")
    fun listarResposta(): Call<List<Resposta>>


    @POST("")
    fun detailResposta(@Body idReposta:Int): Call<Resposta>
}
