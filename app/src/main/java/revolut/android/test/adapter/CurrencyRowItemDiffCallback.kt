package revolut.android.test.adapter

import androidx.recyclerview.widget.DiffUtil
import revolut.android.test.models.Rate


class CurrencyRowItemDiffCallback() :
    DiffUtil.ItemCallback<Rate>() {

    override fun areItemsTheSame(oldItem: Rate, newItem: Rate): Boolean {
        return oldItem.name.equals(newItem.name)
    }

    override fun areContentsTheSame(oldItem: Rate, newItem: Rate): Boolean {
        return oldItem== newItem
    }

}