package revolut.android.test

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import revolut.android.test.base.BaseActivity
import revolut.android.test.databinding.ActivityCurrencyBinding
import revolut.android.test.viewmodels.CurrencyViewModel

class CurrencyActivity : BaseActivity<CurrencyViewModel, ActivityCurrencyBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getViewModel(): Class<CurrencyViewModel> {
        return CurrencyViewModel::class.java
    }
    override fun getLayoutRes(): Int {
        return R.layout.activity_currency
    }
}
