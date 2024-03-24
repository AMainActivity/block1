package ama.test.block1.UI

import ama.test.block1.databinding.MenuBludoItemBinding
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
        binding.tvKolvo.text = "1"
        binding.tvDescription.text = itemMenuBludo.description
        binding.butPlus.setOnClickListener {
            var kolvo = binding.tvKolvo.text.toString().toInt()
            if (kolvo < 15) kolvo++
            binding.tvKolvo.text = kolvo.toString()
        }
        binding.butMinus.setOnClickListener {
            var kolvo = binding.tvKolvo.text.toString().toInt()
            if (kolvo >= 1) kolvo--
            binding.tvKolvo.text = kolvo.toString()
            if (kolvo == 0)
                binding.mbCena.visibility = View.VISIBLE
        }

        binding.mbCena.text = itemMenuBludo.cena
        binding.mbCena.setOnClickListener {
            binding.tvKolvo.text = "1"
            binding.mbCena.visibility = View.INVISIBLE
        }
        binding.root.setOnClickListener {
            onMenuItemClickListener?.invoke(
                binding.tvKolvo.text.toString().toInt() to itemMenuBludo
            )
        }
    }
}