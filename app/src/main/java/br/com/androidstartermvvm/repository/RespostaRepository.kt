package br.com.androidstartermvvm.repository

import br.com.androidstartermvvm.model.dao.ClientDao
import br.com.androidstartermvvm.model.service.RespostaService
import br.com.bb.oewallet.extension.backgroundCall
import retrofit2.create

class RespostaRepository : BaseRepository() {
    private val clientDao: ClientDao = ClientDao()
    private val respostaService: RespostaService =
        ServiceBuilder.create(RespostaService::class.java)

    suspend fun listarResposta() =
        respostaService.listarResposta().backgroundCall(dispatchers.io)

    suspend fun detailResposta(id: Int) =
        respostaService.detailResposta(id).backgroundCall(dispatchers.io)

}

