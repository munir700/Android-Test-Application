package revolut.android.test.utils.numberformat

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

object Mask {
    private fun getCurrencyInstance(): NumberFormat {
        return DecimalFormat("#,##0.00", DecimalFormatSymbols(Locale.getDefault()))
    }

    fun formatCurrency(price: Double): String {
        return getCurrencyInstance().format(price)
    }
}