package revolut.android.test.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import revolut.android.test.MainActivity

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector()
    abstract fun vehicleActivity(): MainActivity
}