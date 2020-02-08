package revolut.android.test.managers

import android.app.Application
import android.content.Context
import javax.inject.Inject

class AppManager @Inject constructor(application: Application) {
    private var appContext: Context = application.applicationContext

    fun getContext(): Context {
        return appContext
    }

    fun getResourceString(resourceName: Int): String? {
        try {
            return getContext().getString(resourceName)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }


}