package ama.test.block1.UI.menu.bludo

import ama.test.block1.entyty.DataMenuBludo
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