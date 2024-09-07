package home.howework.presentation.utils

sealed  class NetState {
    data class Error(val message:String ): NetState()
    object SuccessLoad: NetState()
}
