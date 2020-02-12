package revolut.android.test.viewmodels

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import revolut.android.test.base.BaseViewModel
import revolut.android.test.models.CurrencyRate
import revolut.android.test.models.Rate
import revolut.android.test.models.calculateRate
import revolut.android.test.repository.CurrencyRepository
import revolut.android.test.utils.Currency
import javax.inject.Inject


class CurrencyViewModel @Inject constructor() : BaseViewModel() {


    var apiCurrencyRateCall: Call<CurrencyRate>? = null

    @Inject
    lateinit var currencyRepository: CurrencyRepository


    fun getCurrencyRates(): MutableLiveData<CurrencyRate> {
        return currencyRepository.getCurrenyData(this, apiCurrencyRateCall)
    }

    fun getRateList(
        currencyRate: CurrencyRate,
        currentInputValue: Double
    ): List<Rate> {
        val newRates = mutableListOf<Rate>()
        newRates.add(createRateObject(currencyRate.base, 1.0, currentInputValue, true))
        currencyRate.rates.map { item ->
            newRates.add(createRateObject(item.key, item.value, currentInputValue, false))
        }

        return newRates

    }

    private fun createRateObject(
        name: String,
        value: Double,
        inputValue: Double,
        hasFocus: Boolean
    ): Rate {
        val currency = Currency.valueOf(name)
        return Rate(
            flag = currency.flag,
            name = name,
            desc = currency.desc,
            value = value,
            currency = value.calculateRate(inputValue),
            hasFocus = hasFocus
        )
    }

}