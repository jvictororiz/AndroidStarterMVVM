package br.com.androidstartermvvm.repository

import br.com.androidstartermvvm.util.AppDispatchers

abstract class BaseRepository {
    companion object {
        val dispatchers: AppDispatchers = AppDispatchers()
    }
}