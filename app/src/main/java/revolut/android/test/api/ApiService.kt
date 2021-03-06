package revolut.android.test.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import revolut.android.test.models.CurrencyRate

interface ApiService {

    companion object{
        var currencyName : String = "EUR"
        var currentInputValue : Double = 1.0
    }

    @GET("latest?")
    fun getCurrenyData(@Query("base") base : String ): Call<CurrencyRate>

}