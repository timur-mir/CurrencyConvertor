package home.howework.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import home.howework.data.CurrencyConverterRepo

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideCurrencyConverterRepo(): CurrencyConverterRepo {
        return CurrencyConverterRepo()
    }
}