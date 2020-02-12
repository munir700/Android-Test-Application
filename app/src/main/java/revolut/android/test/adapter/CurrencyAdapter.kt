package revolut.android.test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import revolut.android.test.R
import revolut.android.test.databinding.CurrencyRowBinding
import revolut.android.test.interfaces.CurrenciesEventsListener
import revolut.android.test.models.Rate

class CurrencyAdapter(val context: Context, var rateList: List<Rate>,  val currenciesEventsListener: CurrenciesEventsListener) :
    RecyclerView.Adapter<CurrencyRowViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyRowViewHolder {
        val binding: CurrencyRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.currency_row,
            parent, false
        )
        return CurrencyRowViewHolder(binding)
    }

    fun setData(rateList: List<Rate>){
        this.rateList = rateList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return rateList.size
    }

    override fun onBindViewHolder(holder: CurrencyRowViewHolder, position: Int) {
        val rate: Rate = rateList.get(position)
        holder.bind(rate, currenciesEventsListener)
    }


}