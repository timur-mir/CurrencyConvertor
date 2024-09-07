package home.howework.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideGetCurrencyUseCase(currencyConverterRepo: home.howework.data.CurrencyConverterRepo): home.howework.domain.GetCurrencyUseCase {
        return home.howework.domain.GetCurrencyUseCase(currencyConverterRepo)
    }
}