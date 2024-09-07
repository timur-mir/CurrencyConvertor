package home.howework.presentation.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import home.howework.domain.entity.CurrencyInfoDto
import home.howework.domain.entity.PayLoadDto
import home.howework.domain.entity.RatesDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel@Inject constructor(

    val getCurrencyUseCase: home.howework.domain.GetCurrencyUseCase
)
    : ViewModel() {

    var data =
        PayLoadDto(null, ArrayList<RatesDto>())

    private val _response = MutableStateFlow<CurrencyInfoDto>(
        CurrencyInfoDto(
            "",
            "",
            PayLoadDto(null, listOf())
        )
    )
    val response = _response.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5000,0),
        initialValue = null
    )
    var scope = CoroutineScope(Job() + Dispatchers.IO)

    private val _response2 = MutableStateFlow<CurrencyInfoDto>(
        CurrencyInfoDto(
            "",
            "",
            PayLoadDto(null, listOf())
        )
    )
    val response2 = _response2.asStateFlow()

    private val _response3 = MutableStateFlow<CurrencyInfoDto>(
        CurrencyInfoDto(
            "",
            "",
            PayLoadDto(null, listOf())
        )
    )
    val response3 = _response3.asStateFlow()

    // var scope = CoroutineScope(Job() + Dispatchers.IO)
    val _errorInfo = MutableStateFlow("")
    val errorInfo= _errorInfo.asStateFlow()

    fun reloadCurrency(from:String,to:String) {
        viewModelScope.launch {
            try {
                val response=getCurrencyUseCase.execute(from,to)
                if (response != null) {
                    _response.value = response
                    Log.d("TTM","Ответ: ${response}")
                    _errorInfo.value=""
                }
                else { _errorInfo.value="Ошибка на сервере"}


            } catch (e: Exception) {
                _errorInfo.value="Нет подключения к сети или ошибка в запросе"
        }
            }
    }
    fun reloadCurrency2(from:String,to:String) {
        viewModelScope.launch {
            try {
                val response=getCurrencyUseCase.execute(from,to)
                if (response != null) {
                    _response2.value = response
                    Log.d("TTM","Ответ: ${response}")
                }

            else { _errorInfo.value="Ошибка на сервере"}


        } catch (e: Exception) {
            _errorInfo.value="Нет подключения к сети или ошибка в запросе"
        }
    }
}
    fun reloadCurrency3(from:String,to:String) {
        viewModelScope.launch {
            //  _response.value= CurrencyInfoDto("","", PayLoadDto(null, listOf()))
            try {
                val response=getCurrencyUseCase.execute(from,to)
                if (response != null) {
                    _response3.value = response
                    Log.d("TTM","Ответ: ${response}")
                    Log.d("TTM","Ответ: ${response}")
                }
                else { _errorInfo.value="Ошибка на сервере"}
                response
            } catch (e: Exception) {
                _errorInfo.value="Нет подключения к сети или ошибка в запросе"
            }
        }
    }

}