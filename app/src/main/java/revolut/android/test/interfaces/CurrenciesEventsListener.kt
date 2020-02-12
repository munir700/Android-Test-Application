package revolut.android.test.interfaces

import revolut.android.test.models.Rate

interface CurrenciesEventsListener {
    fun onAmountChanged(amount: CharSequence)
    fun onRowClicked(rate: Rate)
}