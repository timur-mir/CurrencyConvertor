package home.howework.currencyconverter.di

import dagger.Component
import home.howework.currencyconverter.presentation.MainViewModelFactory

@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        PresentationModule::class
    ]
)
interface AppComponent {
    fun mainViewModelFactory(): MainViewModelFactory

}