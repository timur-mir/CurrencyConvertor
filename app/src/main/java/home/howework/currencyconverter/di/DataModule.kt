package home.howework.currencyconverter.di

import dagger.Module
import dagger.Provides
import home.howework.currencyconverter.data.CurrencyConverterRepo

@Module
class DataModule {
    @Provides
    fun provideCurrencyConverterRepo(): CurrencyConverterRepo {
        return CurrencyConverterRepo()
    }
}