package ama.test.block1.UI

import androidx.recyclerview.widget.DiffUtil

object MenuBludoDiffCallback : DiffUtil.ItemCallback<DataMenuBludo>() {

    override fun areItemsTheSame(
        oldItem: DataMenuBludo,
        newItem: DataMenuBludo
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: DataMenuBludo,
        newItem: DataMenuBludo
    ): Boolean {
        return oldItem == newItem
    }
}