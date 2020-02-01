package revolut.android.test

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import revolut.android.test.base.BaseActivity
import revolut.android.test.base.BaseViewModel
import revolut.android.test.viewmodels.MainViewModel

class MainActivity : BaseActivity<MainViewModel, ViewDataBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }
}
