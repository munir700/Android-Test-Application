package revolut.android.test.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import revolut.android.test.BuildConfig
import java.util.concurrent.TimeUnit

class ApiHttpClient {
    fun getHTTPClient(headers: Map<String, String>): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addNetworkInterceptor(StethoInterceptor())
        httpClient.connectTimeout(2, TimeUnit.MINUTES)
        httpClient.readTimeout(75, TimeUnit.SECONDS)
        httpClient.writeTimeout(45, TimeUnit.SECONDS)

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        if (BuildConfig.API_LOGGING)
            httpClient.addInterceptor(logging)

        return httpClient.build()

    }
}