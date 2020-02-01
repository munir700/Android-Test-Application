package revolut.android.test.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import revolut.android.test.MainActivity

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector()
    abstract fun mainActivity(): MainActivity
}