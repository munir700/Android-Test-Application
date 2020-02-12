package revolut.android.test.adapter

import android.text.InputType
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import revolut.android.test.databinding.CurrencyRowBinding
import revolut.android.test.interfaces.CurrenciesEventsListener
import revolut.android.test.models.Rate
import revolut.android.test.utils.numberformat.Mask
import kotlin.properties.Delegates

class CurrencyRowViewHolder(val rowCurrencyBinding: CurrencyRowBinding) :
    RecyclerView.ViewHolder(rowCurrencyBinding.root) {
    private var currentInput: Double by Delegates.notNull()
    private var textWatcher: TextWatcher? = null

    fun bind(rate: Rate, currenciesEventsListener: CurrenciesEventsListener) {
        rowCurrencyBinding.rate = rate
        currentInput = rate.currency
        setCurrency(rate.value, rowCurrencyBinding.currencyValueTextField)

        rowCurrencyBinding.currencyValueTextField.run {
            textWatcher?.let { removeTextChangedListener(textWatcher) }
            if (rate.hasFocus) {
                inputType = InputType.TYPE_CLASS_NUMBER.or(InputType.TYPE_NUMBER_FLAG_DECIMAL)
                /*textWatcher = doAfterTextChanged { text ->
                    text?.let { currenciesEventsListener.onAmountChanged(it) }
                }*/
            } else {
                keyListener = null
                inputType = InputType.TYPE_NULL

            }

            itemView.setOnClickListener {
                currenciesEventsListener.onRowClicked(rate)
            }
        }
    }

    private fun setCurrency(currency: Double, editText: EditText) {
        currentInput = currency
        if (currentInput > 0)
            editText.setText(Mask.formatCurrency(currentInput))
        else
            editText.text = null
    }

}