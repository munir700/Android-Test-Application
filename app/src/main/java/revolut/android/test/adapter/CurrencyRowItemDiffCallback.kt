package revolut.android.test.adapter

import androidx.recyclerview.widget.DiffUtil
import revolut.android.test.models.Rate

object CurrencyRowItemDiffCallback : DiffUtil.ItemCallback<Rate>() {
    override fun areItemsTheSame(oldItem: Rate, newItem: Rate): Boolean =
        oldItem.flag == newItem.flag

    override fun areContentsTheSame(oldItem: Rate, newItem: Rate): Boolean =
        oldItem == newItem
}