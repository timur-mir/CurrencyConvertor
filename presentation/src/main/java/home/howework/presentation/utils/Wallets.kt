package home.howework.presentation.utils

import home.howework.currencyconverter.utils.CurrenciesWorld
import home.howework.currencyconverter.utils.convertToUSD


sealed class Wallets() {

    class VirtualWallets() : Wallets() {
        var totalAmount: Double = 0.0
        var rub: Double = 0.0
        var eur: Double = 0.0
        var usd: Double = 0.0
        var uzs: Double = 0.0

        fun addMoney(currencyType: CurrenciesWorld, amountOfMoney: Double): Double {
            when (currencyType) {
                CurrenciesWorld.EURO -> {
                    eur += amountOfMoney;
                    return eur
                }

                CurrenciesWorld.RUBLE -> {
                    rub += amountOfMoney;
                    return rub
                }

                CurrenciesWorld.DOLLAR -> {
                    usd += amountOfMoney;
                    return usd
                }

                CurrenciesWorld.SUMUZ -> {
                    uzs += amountOfMoney;
                    return uzs
                }

            }
        }

        override fun moneyInUSD(item:CurrenciesWorld): Double {
            when (item) {
                CurrenciesWorld.EURO->{if (eur!=0.0){
                    totalAmount=CurrenciesWorld.EURO.convertToUSD(eur)}}
                CurrenciesWorld.RUBLE->{if (rub!=0.0){
                    totalAmount=CurrenciesWorld.RUBLE.convertToUSD(rub)}}
                CurrenciesWorld.DOLLAR->{if (usd!=0.0){
                    totalAmount=CurrenciesWorld.DOLLAR.convertToUSD(usd)}}
                CurrenciesWorld.SUMUZ->{if (uzs!=0.0){
                    totalAmount=CurrenciesWorld.SUMUZ.convertToUSD(uzs)}}
            }
            return totalAmount
        }

        fun clearTotalAmount() {
            totalAmount = 0.0
            rub = 0.0
            eur = 0.0
            usd = 0.0
            uzs = 0.0
        }

    }

//    class RealWallets() : Wallets() {
//
//        private var rubles: MutableMap<Int, Int> = mutableMapOf<Int, Int>()
//        private var euros: MutableMap<Int, Int> = mutableMapOf<Int, Int>()
//        private var dollars: MutableMap<Int, Int> = mutableMapOf<Int, Int>()
//
//        fun addMoney(
//            type: CurrenciesWorld,
//            nominal: Int,
//            amount: Int
//        ) {
//            when (type) {
//
//                CurrenciesWorld.RUBLE -> {
//                    val currentAmount = rubles[nominal]
//                    if (currentAmount == null) {
//                        rubles[nominal] = amount
//                    } else
//                        rubles[nominal] = currentAmount + amount
//                }
//
//                CurrenciesWorld.EURO -> {
//                    val currentAmount = euros[nominal]
//                    if (currentAmount == null) {
//                        euros[nominal] = amount
//                    } else
//                        euros[nominal] = currentAmount + amount
//                }
//
//                CurrenciesWorld.DOLLAR -> {
//                    val currentAmount = dollars[nominal]
//                    if (currentAmount == null) {
//                        dollars[nominal] = amount
//                    } else
//                        dollars[nominal] = currentAmount + amount
//                }
//
//                CurrenciesWorld.SUMUZ -> {}
//            }
//        }
//
//        override fun moneyInUSD(item: CurrenciesWorld): Double {
//            var totalAmount: Double = CurrenciesWorld.RUBLE.convertToUSD(totalAmountOfRubles()) +
//                    CurrenciesWorld.EURO.convertToUSD(totalAmountOfEuros()) +
//                    CurrenciesWorld.DOLLAR.convertToUSD(totalAmountOfDollars())
//            return totalAmount
//        }
//
//        fun totalAmountOfRubles(): Double {
//            var sum = 0.0
//            rubles.forEach {
//                sum += (it.key * it.value).toDouble()
//            }
//            return sum
//        }
//
//        fun totalAmountOfEuros(): Double {
//            var sum = 0.0
//            euros.forEach {
//                sum += (it.key * it.value).toDouble()
//            }
//            return sum
//        }
//
//        fun totalAmountOfDollars(): Double {
//            var sum = 0.0
//            dollars.forEach {
//                sum += (it.key * it.value).toDouble()
//            }
//            return sum
//        }
//
//    }

    abstract fun moneyInUSD(item:CurrenciesWorld): Double
}