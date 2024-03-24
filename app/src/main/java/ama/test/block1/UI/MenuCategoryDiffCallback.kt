package ama.test.block1.UI

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