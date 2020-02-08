package revolut.android.test.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import revolut.android.test.utils.ApiUtils
import revolut.android.test.api.ApiHttpClient
import revolut.android.test.api.ApiService
import revolut.android.test.api.converter.ApiConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
class ApiClientModule {
    @Provides
    @Singleton
    fun getApiService(): ApiService {

        val apiClient = Retrofit.Builder()
            .baseUrl(ApiUtils.API_BASE_URL)
            .addConverterFactory(ApiConverterFactory())
            .addConverterFactory(GsonConverterFactory.create(GsonApi()))
            .client(ApiHttpClient().getHTTPClient(HashMap<String, String>()))
            .build()

        return apiClient.create(ApiService::class.java)

    }
    @Provides
    @Singleton
    fun GsonApi(): Gson {
        return GsonBuilder()
            .create()
    }
}