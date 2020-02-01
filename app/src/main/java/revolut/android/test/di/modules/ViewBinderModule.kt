package revolut.android.test.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import revolut.android.test.di.ViewModelFactory
import revolut.android.test.di.modules.ViewModelKey
import revolut.android.test.viewmodels.MainViewModel

@Module
abstract class ViewBindModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMovieViewModel(mainViewModel: MainViewModel): ViewModel
}