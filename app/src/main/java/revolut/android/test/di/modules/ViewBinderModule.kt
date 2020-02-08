package revolut.android.test.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import revolut.android.test.di.ViewModelFactory
import revolut.android.test.di.ViewModelKey
import revolut.android.test.viewmodels.CurrencyViewModel

@Module
abstract class ViewBinderModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyViewModel::class)
    abstract fun bindMainViewModel(currencyViewModel: CurrencyViewModel): ViewModel
}