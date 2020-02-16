package revolut.android.test.views

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.SimpleItemAnimator
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import revolut.android.test.R
import revolut.android.test.adapter.CurrencyAdapter
import revolut.android.test.api.ApiService
import revolut.android.test.base.BaseActivity
import revolut.android.test.databinding.ActivityCurrencyBinding
import revolut.android.test.enums.ViewModelEventsEnum
import revolut.android.test.interfaces.CurrenciesEventsListener
import revolut.android.test.models.Rate
import revolut.android.test.utils.Logger
import revolut.android.test.utils.numberformat.Mask
import revolut.android.test.viewmodels.CurrencyViewModel
import java.util.concurrent.TimeUnit


class CurrencyActivity : BaseActivity<CurrencyViewModel, ActivityCurrencyBinding>(),
    CurrenciesEventsListener {

    private var scrollRecyclerviewTop: Boolean = false
    private var currencyValue = ApiService.currentInputValue

    lateinit var currencyAdapter: CurrencyAdapter


    override fun getViewModel(): Class<CurrencyViewModel> {
        return CurrencyViewModel::class.java
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_currency
    }

    override fun onObserve(event: ViewModelEventsEnum, eventMessage: String?) {
        super.onObserve(event, eventMessage)
        when (event) {
            ViewModelEventsEnum.NO_INTERNET_CONNECTION -> {
                Logger("NO_INTERNET_CONNECTION", "no internet")
                onApiRequestFailed(eventMessage)
                binding.skeleton?.visibility = View.GONE
            }
            ViewModelEventsEnum.ON_API_REQUEST_FAILURE -> {
                Logger("ON_API_REQUEST_FAILURE", "Api failure $eventMessage")
                onApiRequestFailed(eventMessage)
                binding.skeleton?.visibility = View.GONE
            }
            ViewModelEventsEnum.ON_API_CALL_START -> {
                Logger("ON_API_CALL_START", "start")
                if (viewModel.rateList.isEmpty()) {
                    binding.skeleton?.visibility = View.VISIBLE
                }
            }
            ViewModelEventsEnum.ON_API_CALL_STOP -> {
                Logger("ON_API_CALL_STOP", "stop")
                binding.skeleton?.visibility = View.GONE
                binding.recyclerResults.visibility = View.VISIBLE
            }
            else -> {
                binding.skeleton?.visibility = View.GONE
                Logger("ON_API_CALL_STOP", "stop")
            }
        }
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
            Logger(
                "Exception",
                "Error while initialize UI components and message =" + e.message
            )
        }
    }

    private fun callApiTimer() {
        val disposable = Observable.interval(0, 1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { loadCurrency() }

        viewModel.compositeDisposable.add(disposable)

    }

    private fun loadCurrency() {
        viewModel.getCurrencyRates().observe(this, Observer {
            //Logger("loadCurrency()", "currencyValue $currencyValue")
            viewModel.rateList = viewModel.getRateList(it, currencyValue)
            currencyAdapter.submitList(viewModel.rateList)
            if (scrollRecyclerviewTop && it.base.equals(ApiService.currencyName)) {
                //Logger("loadCurrency()", "smoothScrollToPosition to 0")
                scrollRecyclerviewTop = false;
                binding.recyclerResults.post({ binding.recyclerResults.smoothScrollToPosition(0) })
            }

            //Logger("loadCurrency()", "item count ${currencyAdapter.itemCount}")
        })
    }

    override fun onAmountChanged(amount: CharSequence) {
        //Logger("onAmountChanged_1", "amount $amount")
        if (amount.isNotEmpty()) {
            currencyValue = Mask.removeCurrencyFormat(amount)
        }
        //Logger("onAmountChanged_2", "currencyValue $currencyValue")
    }

    override fun onRowClicked(rate: Rate) {
        /*Logger(
            "onRowClicked_1",
            "name " + ApiService.currencyName + " currentInputValue " + ApiService.currentInputValue
        )*/
        ApiService.currencyName = rate.name
        ApiService.currentInputValue = rate.currency
        scrollRecyclerviewTop = true
       /* Logger(
            "onRowClicked_2",
            "name " + ApiService.currencyName + " currentInputValue " + ApiService.currentInputValue
        )*/
    }


}
