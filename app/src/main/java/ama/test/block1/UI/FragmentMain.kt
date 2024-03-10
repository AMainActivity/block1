package ama.test.block1.UI

import ama.test.block1.MainActivity
import ama.test.block1.MainAdapter
import ama.test.block1.MenuItem
import ama.test.block1.R
import ama.test.block1.databinding.FragmentMainBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class FragmentMain : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuIyemList = createMenuItemList()
        val adapter = MainAdapter(menuIyemList)
        binding.recyclerView.adapter = adapter
        adapter.onMenuItemClickListener = {
            (requireActivity() as MainActivity).setCurrentFragment(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createMenuItemList(): ArrayList<MenuItem> {
        val list = ArrayList<MenuItem>()
        list.add(
            MenuItem(
                getString(R.string.frgmnt_main_menu),
                R.drawable.rv_item_menu,
                R.id.navigation_menu
            )
        )
        list.add(
            MenuItem(
                getString(R.string.frgmnt_main_akcii),
                R.drawable.rv_item_akcii,
                R.id.navigation_akcii
            )
        )
        list.add(
            MenuItem(
                getString(R.string.frgmnt_main_orders),
                R.drawable.rv_item_otzyvy,
                R.id.navigation_orders
            )
        )
        list.add(
            MenuItem(
                getString(R.string.frgmnt_main_delivery),
                R.drawable.rv_item_delivery,
                R.id.navigation_delivery
            )
        )
        return list
    }

    companion object {
        const val NAME = "FragmentMain"
        fun newInstance(): FragmentMain {
            return FragmentMain()
        }
    }
}