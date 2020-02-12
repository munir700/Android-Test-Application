package revolut.android.test.interfaces

import revolut.android.test.enums.ViewModelEventsEnum

interface ViewModelCallBackObserver<T> {
    fun onObserve(event: ViewModelEventsEnum?, eventMessage: T)
}