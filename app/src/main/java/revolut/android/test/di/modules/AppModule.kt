package revolut.android.test.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import revolut.android.test.managers.AppManager
import revolut.android.test.utils.NetworkUtils
import javax.inject.Singleton

@Module(includes = [ViewBinderModule::class])
class AppModule {
    @Provides
    @Singleton
    fun providesAppManager(application: Application): AppManager {
        return AppManager(application)
    }


    @Provides
    @Singleton
    fun providesNetworkUtils(application: Application): NetworkUtils {
        return NetworkUtils(application)
    }
}