package revolut.android.test

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.SimpleItemAnimator
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import revolut.android.test.adapter.CurrencyAdapter
import revolut.android.test.api.ApiService
import revolut.android.test.base.BaseActivity
import revolut.android.test.databinding.ActivityCurrencyBinding
import revolut.android.test.interfaces.CurrenciesEventsListener
import revolut.android.test.models.Rate
import revolut.android.test.models.calculateRate
import revolut.android.test.viewmodels.CurrencyViewModel
import java.util.concurrent.TimeUnit

class CurrencyActivity : BaseActivity<CurrencyViewModel, ActivityCurrencyBinding>(),
    CurrenciesEventsListener {

    private var currencyValue = ApiService.currentInputValue

    lateinit var currencyAdapter: CurrencyAdapter

    override fun getViewModel(): Class<CurrencyViewModel> {
        return CurrencyViewModel::class.java
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_currency
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        callApiTimer()
    }

    private fun initUI() {
        try {
            (binding.recyclerResults.getItemAnimator() as SimpleItemAnimator).supportsChangeAnimations =
                false
            currencyAdapter = CurrencyAdapter(
                this@CurrencyActivity, this
            )
            binding.recyclerResults.setAdapter(currencyAdapter)

        } catch (e: Exception) {
            Log.e(
                "Exception",
                "Error while initialize UI components and message =" + e.message
            )
        }
    }

    private fun callApiTimer() {
        val disposable = Observable.interval(2, 1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { loadCurrency() }

        viewModel.compositeDisposable.add(disposable)

    }

    private fun loadCurrency() {
        viewModel.getCurrencyRates().observe(this, Observer {
            Log.e("getCurrencyRates()", "currencyValue $currencyValue")
            viewModel.rateList = viewModel.getRateList(it, currencyValue)
            currencyAdapter.submitList(viewModel.rateList)

        })
    }

    override fun onAmountChanged(amount: CharSequence) {
        Log.e("onAmountChanged", "amount $amount")
        if (amount.isNotEmpty()) {
            currencyValue = amount.toString().toDouble()
        }
        Log.e("onAmountChanged", "currencyValue $currencyValue")
    }

    override fun onRowClicked(rate: Rate) {
        Log.e(
            "onRowClicked_1",
            "name " + ApiService.currencyName + " currentInputValue " + ApiService.currentInputValue
        )
        ApiService.currencyName = rate.name
        ApiService.currentInputValue = rate.currency
        currencyValue = rate.currency
        updateList(rate.currency)
        Log.e(
            "onRowClicked_1",
            "name " + ApiService.currencyName + " currentInputValue " + ApiService.currentInputValue
        )
    }
    private fun updateList(inputValue: Double) {
        val list = currencyAdapter.currentList

        val newList = list.map {
            val c = if (inputValue > 0)
                it.value.calculateRate(inputValue)
            else
                0.0

            it.copy(currency = c)
        }

        currencyAdapter.submitList(newList)
    }

}
