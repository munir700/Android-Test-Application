package revolut.android.test

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.SimpleItemAnimator
import revolut.android.test.adapter.CurrencyAdapter
import revolut.android.test.base.BaseActivity
import revolut.android.test.databinding.ActivityCurrencyBinding
import revolut.android.test.interfaces.CurrenciesEventsListener
import revolut.android.test.models.Rate
import revolut.android.test.viewmodels.CurrencyViewModel

class CurrencyActivity : BaseActivity<CurrencyViewModel, ActivityCurrencyBinding>(),
    CurrenciesEventsListener {

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
        loadCurrency(1.0)
    }

    private fun initUI() {
        try {
            (binding.recyclerResults.getItemAnimator() as SimpleItemAnimator).supportsChangeAnimations =
                false
            currencyAdapter = CurrencyAdapter(
                this@CurrencyActivity,
                ArrayList(), this
            )
            binding.recyclerResults.setAdapter(currencyAdapter)

        } catch (e: Exception) {
            Log.e(
                "Exception",
                "Error while initialize UI components and message =" + e.message
            )
        }
    }


    private fun loadCurrency(currentInputValue: Double) {
        viewModel.getCurrencyRates().observe(this, Observer {
            var rateList = viewModel.getRateList(it, currentInputValue)
            currencyAdapter.setData(rateList)

        })
    }

    override fun onAmountChanged(amount: CharSequence) {
    }

    override fun onRowClicked(row: Rate) {
    }

}
