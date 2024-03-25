package ama.test.block1.UI.menu.category

import ama.test.block1.entyty.DataMenuCategory
import androidx.recyclerview.widget.DiffUtil

object MenuCategoryDiffCallback : DiffUtil.ItemCallback<DataMenuCategory>() {

    override fun areItemsTheSame(
        oldItem: DataMenuCategory,
        newItem: DataMenuCategory
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: DataMenuCategory,
        newItem: DataMenuCategory
    ): Boolean {
        return oldItem == newItem
    }
}