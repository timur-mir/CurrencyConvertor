package home.howework.currencyconverter.presentation

sealed  class NetState {
    data class Error(val message:String ): NetState()
    object SuccessLoad: NetState()
}
