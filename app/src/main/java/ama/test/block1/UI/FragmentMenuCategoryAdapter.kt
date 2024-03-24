package ama.test.block1.UI

import ama.test.block1.databinding.MenuCategoryItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso

class FragmentMenuCategoryAdapter :
    ListAdapter<DataMenuCategory, MenuCategoryViewHolder>(MenuCategoryDiffCallback) {

     var onMenuItemClickListener: ((Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuCategoryViewHolder {
        val binding = MenuCategoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MenuCategoryViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MenuCategoryViewHolder, position: Int) {
        val itemMenuCategory = getItem(position)
        val binding = holder.binding

        Picasso.get().load(itemMenuCategory.urlImage)
            .into(binding.itemBg)
        binding.itemText.text = itemMenuCategory.name
        binding.root.setOnClickListener {
            onMenuItemClickListener?.invoke(
                itemMenuCategory.id
            )
        }
    }
}