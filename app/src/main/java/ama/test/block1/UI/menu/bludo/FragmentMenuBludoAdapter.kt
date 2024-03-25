package ama.test.block1.UI.menu.bludo

import ama.test.block1.databinding.MenuBludoItemBinding
import ama.test.block1.entyty.DataMenuBludo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso

class FragmentMenuBludoAdapter :
    ListAdapter<DataMenuBludo, MenuBludoViewHolder>(MenuBludoDiffCallback) {

    var onMenuItemClickListener: ((Pair<Int, DataMenuBludo>) -> Unit)? = null
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
        binding.mbCena.visibility = View.VISIBLE
        binding.tvKolvo.text = FIRST_ITEM_STRING
        binding.tvDescription.text = itemMenuBludo.description
        binding.butPlus.setOnClickListener {
            var kolvo = binding.tvKolvo.text.toString().toInt()
            if (kolvo < LIMIT_ITEMS) kolvo++
            binding.tvKolvo.text = kolvo.toString()
        }
        binding.butMinus.setOnClickListener {
            var kolvo = binding.tvKolvo.text.toString().toInt()
            if (kolvo >= FIRST_ITEM) kolvo--
            binding.tvKolvo.text = kolvo.toString()
            if (kolvo == ZERO_ITEM)
                binding.mbCena.visibility = View.VISIBLE
        }

        binding.mbCena.text = itemMenuBludo.cena
        binding.mbCena.setOnClickListener {
            binding.tvKolvo.text = FIRST_ITEM_STRING
            binding.mbCena.visibility = View.INVISIBLE
        }
        binding.root.setOnClickListener {
            onMenuItemClickListener?.invoke(
                binding.tvKolvo.text.toString().toInt() to itemMenuBludo
            )
        }
    }

    companion object {
        private const val LIMIT_ITEMS = 15
        private const val ZERO_ITEM = 0
        private const val FIRST_ITEM = 1
        private const val FIRST_ITEM_STRING = "1"
    }
}