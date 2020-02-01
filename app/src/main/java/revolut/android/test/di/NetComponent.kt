package revolut.android.test.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import revolut.android.test.TestApp
import revolut.android.test.di.modules.ActivityBuilderModule
import revolut.android.test.di.modules.ApiClientModule
import revolut.android.test.di.modules.ViewBinderModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [(ViewBinderModule::class), (ActivityBuilderModule::class), (AndroidInjectionModule::class),
        AndroidSupportInjectionModule::class, (ApiClientModule::class)]
)
interface NetComponent {

    fun inject(app: TestApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): NetComponent
    }


}