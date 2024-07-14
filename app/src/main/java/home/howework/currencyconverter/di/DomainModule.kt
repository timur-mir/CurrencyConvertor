package home.howework.currencyconverter.di

import dagger.Module
import dagger.Provides
import home.howework.currencyconverter.data.CurrencyConverterRepo
import home.howework.currencyconverter.domain.GetCurrencyUseCase

@Module
class DomainModule {
    @Provides
    fun provideGetCurrencyUseCase(currencyConverterRepo: CurrencyConverterRepo):GetCurrencyUseCase{
        return GetCurrencyUseCase(currencyConverterRepo)
    }
}