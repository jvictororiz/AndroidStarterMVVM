package br.com.androidstartermvvm.ui.main

import br.com.androidstartermvvm.model.entities.Resposta
import br.com.androidstartermvvm.repository.RespostaRepository
import br.com.androidstartermvvm.ui.base.BaseViewModel
import br.com.androidstartermvvm.ui.base.SingleLiveEvent

class HomeViewModel : BaseViewModel() {
    private val respostaRepository: RespostaRepository = RespostaRepository()
    val resposta = SingleLiveEvent<List<Resposta>>()
    val respostaDetalhe = SingleLiveEvent<Resposta>()
    val snackbarMessage = SingleLiveEvent<String>()

    fun listResposta() = launchWithLoad {
        val result = respostaRepository.listarResposta()
        if (result.isSuccessful()) {
            resposta.value = result.data
        } else {
            snackbarMessage.value = result.throwable?.message
        }
    }



    fun detailResposta(atributo: Resposta?) = launchWithLoad {
        val result = atributo?.id?.let { respostaRepository.detailResposta(it) }
        if (result != null && result.isSuccessful()) {
            respostaDetalhe.value = result.data
        } else {
            snackbarMessage.value = result?.throwable?.message
        }
    }

}
