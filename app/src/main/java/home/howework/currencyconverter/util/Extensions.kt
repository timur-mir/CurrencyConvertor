package home.howework.currencyconverter.util

import android.text.Editable
import android.widget.EditText
import home.howework.currencyconverter.presentation.MainActivity
import java.text.DecimalFormat

fun Editable.toDouble() = toString().toDouble()
fun EditText.toDouble():Double =try {
    text.toString().toDouble()
} catch (e:NumberFormatException){
    e.printStackTrace()
    0.0
}
fun Double.format(fracDigits:Int):String{
    val df = DecimalFormat()
    df.setMaximumFractionDigits(fracDigits)
    return df.format(this)
}

fun CurrenciesWorld.convertToUSD(amount: Double): Double {
    return when (this) {
        CurrenciesWorld.RUBLE -> amount / MainActivity.CurrencyConverter.dollarToRuble
        CurrenciesWorld.EURO -> (amount * MainActivity.CurrencyConverter.euroToRuble) / MainActivity.CurrencyConverter.dollarToRuble
        CurrenciesWorld.DOLLAR -> amount*MainActivity.CurrencyConverter.sumToDollar
        CurrenciesWorld.SUMUZ -> amount / MainActivity.CurrencyConverter.sumToDollar
    }
}
val  CurrenciesWorld.checkNationalCurrency:Boolean
    get() = this == CurrenciesWorld.nationalCurrency