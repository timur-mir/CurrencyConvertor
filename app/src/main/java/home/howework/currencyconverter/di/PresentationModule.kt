package home.howework.currencyconverter.di

import dagger.Module
import dagger.Provides
import home.howework.currencyconverter.data.CurrencyConverterRepo
import home.howework.currencyconverter.domain.GetCurrencyUseCase
import home.howework.currencyconverter.presentation.MainViewModel
import home.howework.currencyconverter.presentation.MainViewModelFactory

@Module

class PresentationModule {
    @Provides
    fun provideMainViewModel(
        repo: CurrencyConverterRepo,
        useCase: GetCurrencyUseCase
    ): MainViewModel {
        return MainViewModel(useCase)
    }
@Provides
fun provideMainViewModelFactory(mainViewModel: MainViewModel):MainViewModelFactory{
    return MainViewModelFactory(mainViewModel)
}
}