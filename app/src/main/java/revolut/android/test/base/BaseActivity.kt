package revolut.android.test.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import revolut.android.test.enums.ViewModelEventsEnum
import revolut.android.test.interfaces.ViewModelCallBackObserver
import revolut.android.test.utils.showToast
import javax.inject.Inject

abstract class BaseActivity< VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity(),
    ViewModelCallBackObserver<String> {

    lateinit var binding: DB
    lateinit var viewModel: VM

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract fun getViewModel(): Class<VM>

    @LayoutRes
    abstract fun getLayoutRes(): Int

    override fun onObserve(event: ViewModelEventsEnum, eventMessage: String?) {

    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        /** View Model Provider Generic handling, Child will have to override getViewModel. */
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel())
        viewModel.addObserver(this)
        super.onCreate(savedInstanceState)
        /** To generically handle Data binding */
        binding = DataBindingUtil.setContentView(this, getLayoutRes())
        /** To support vector drawables */
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    /**
     * on Server Request Failed.
     */
    open fun onApiRequestFailed(message: String?) {

        showToast(message)
    }

}