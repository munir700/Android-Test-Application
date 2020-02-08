package revolut.android.test.base

import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ObservableViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getCompositeDisposable() : CompositeDisposable = compositeDisposable

    override fun onCleared() {
        compositeDisposable.clear()

    }
}