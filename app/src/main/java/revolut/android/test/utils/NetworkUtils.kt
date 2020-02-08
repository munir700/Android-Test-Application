package revolut.android.test.utils

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class NetworkUtils @Inject constructor(val context: Context) {

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun isNetworkAvailable(): Boolean {
        return isNetworkAvailable(context)
    }


}