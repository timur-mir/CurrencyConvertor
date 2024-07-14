package home.howework.currencyconverter.presentation

sealed  class NetState {
    data class Error(val message:String ): NetState()
    object SuccessLoad: NetState()
}
//sealed class AppStateMain {
//    data class SuccessLoadTop250(val filmItemListTop250: List<FilmItem>): AppStateMain()
//    data class SuccessLoadMostPopular(val filmItemListMostPopular: List<FilmItem>): AppStateMain()
//    data class SuccessSearching (val filmSearchList: List<FilmSearchResultsDTO>): AppStateMain()
//    data class Error(val error: Throwable): AppStateMain()
//    object Loading: AppStateMain()
//}