package ama.test.block1.UI

import ama.test.block1.databinding.MenuBludoItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso

class FragmentMenuBludoAdapter :
    ListAdapter<DataMenuBludo, MenuBludoViewHolder>(MenuBludoDiffCallback) {

    var onMenuItemClickListener: ((Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuBludoViewHolder {
        val binding = MenuBludoItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MenuBludoViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MenuBludoViewHolder, position: Int) {
        val itemMenuBludo = getItem(position)
        val binding = holder.binding

        Picasso.get().load(itemMenuBludo.urlImage)
            .into(binding.itemBg)
        binding.tvName.text = itemMenuBludo.name
        binding.tvDescription.text = itemMenuBludo.description
        binding.root.setOnClickListener {
            onMenuItemClickListener?.invoke(
                itemMenuBludo.id
            )
        }
    }
}