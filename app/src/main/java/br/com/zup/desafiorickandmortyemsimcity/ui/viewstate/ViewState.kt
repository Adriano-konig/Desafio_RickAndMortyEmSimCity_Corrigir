package br.com.zup.desafiorickandmortyemsimcity.ui.viewstate

sealed class ViewState<out T> {
    data class Success<T>(val data: T) : ViewState<T>()
    data class Error(val throwable: Throwable) : ViewState<Nothing>()
    data class EmptyList<T>(val data: T) : ViewState<T>()
}