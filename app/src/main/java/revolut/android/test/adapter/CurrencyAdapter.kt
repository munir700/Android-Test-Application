package revolut.android.test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import revolut.android.test.R
import revolut.android.test.databinding.CurrencyRowBinding
import revolut.android.test.interfaces.CurrenciesEventsListener
import revolut.android.test.models.Rate


class CurrencyAdapter(
    val context: Context,
    val currenciesEventsListener: CurrenciesEventsListener
) :
    ListAdapter<Rate, CurrencyRowViewHolder>(CurrencyRowItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyRowViewHolder {
        val binding: CurrencyRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.currency_row,
            parent, false
        )
        return CurrencyRowViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CurrencyRowViewHolder, position: Int) {
        val rate: Rate = getItem(position)
        holder.bind(rate, currenciesEventsListener)
    }

}