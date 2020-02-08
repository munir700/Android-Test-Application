package revolut.android.test.api

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import revolut.android.test.models.CurrencyRate

interface ApiService {

    @GET("")
    fun getCurrenyData(): Single<Response<CurrencyRate>>

}