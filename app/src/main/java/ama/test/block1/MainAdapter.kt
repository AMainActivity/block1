package ama.test.block1

import ama.test.block1.databinding.MenuItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(private val menuItems: List<MenuItem>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    var onMenuItemClickListener: ((Int) -> Unit)? = null

    class ViewHolder(
        val binding: MenuItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MenuItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val menuItem: MenuItem = menuItems[position]
        val textView = viewHolder.binding.itemText
        val binding = viewHolder.binding
        textView.text = menuItem.name
        val bg = viewHolder.binding.itemBg
        bg.setBackgroundResource(menuItem.resId)
        binding.root.setOnClickListener {
            onMenuItemClickListener?.invoke(menuItem.menuId)
        }
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }
}