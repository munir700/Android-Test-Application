package revolut.android.test.models

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

data class Rate(
    val flag: Int,
    val name: String,
    val desc: String,
    val value: Double,
    val currency: Double,
    val hasFocus: Boolean
)

fun Double.calculateRate(inputValue: Double): Double {
    return inputValue * this
}
private fun getCurrencyInstance(): NumberFormat {
    return DecimalFormat("#,##0.00", DecimalFormatSymbols(Locale.getDefault()))
}

fun formatCurrency(price: Double): String {
    return getCurrencyInstance().format(price)
}