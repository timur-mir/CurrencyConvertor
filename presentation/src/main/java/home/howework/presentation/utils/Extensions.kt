package home.howework.currencyconverter.utils

import android.text.Editable
import android.widget.EditText
import home.howework.currencyconverter.utils.CurrenciesWorld
import java.text.DecimalFormat

fun Editable.toDouble() = toString().toDouble()
fun EditText.toDouble():Double =try {
    text.toString().toDouble()
} catch (e:NumberFormatException){
    e.printStackTrace()
    0.0
}
fun Double.format(fractDigits:Int):String{
    val df = DecimalFormat()
    df.setMaximumFractionDigits(fractDigits)
    return df.format(this)
}

fun CurrenciesWorld.convertToUSD(amount: Double,selectedValuteCur:Double): Double {
    return when (this) {
        CurrenciesWorld.RUBLE -> amount /selectedValuteCur
        CurrenciesWorld.EURO -> amount*selectedValuteCur
        CurrenciesWorld.DOLLAR -> amount*selectedValuteCur
        CurrenciesWorld.SUMUZ -> amount /selectedValuteCur
    }
}
val  CurrenciesWorld.checkNationalCurrency:Boolean
    get() = this == CurrenciesWorld.nationalCurrency