package revolut.android.test

import android.app.Activity
import android.app.Application
import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import revolut.android.test.di.DaggerNetComponent
import javax.inject.Inject

class TestApp : Application(), HasActivityInjector {
    @Inject
    lateinit var activityDispatchingInjector: DispatchingAndroidInjector<Activity>

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }


    override fun onCreate() {
        super.onCreate()
        DaggerNetComponent.builder()
            .application(this).build().inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingInjector
    }
}