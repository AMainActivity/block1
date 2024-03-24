package ama.test.block1.UI

import ama.test.block1.MainActivity
import ama.test.block1.MyApp
import ama.test.block1.R
import ama.test.block1.databinding.FragmentMenuBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class FragmentMenuCategory : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private lateinit var adapter: FragmentMenuCategoryAdapter
    private val dataList: List<DataMenuCategory> by lazy {
        (requireActivity().application as MyApp).dataMenuCategory
    }
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentMenuBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.category_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setAdapter() {
        adapter = FragmentMenuCategoryAdapter()
        binding.frhmntMenuRv.adapter = adapter
        binding.frhmntMenuRv.itemAnimator = null
        adapter.submitList(dataList)
    }

    private fun setAdapterClick() {
        adapter.onMenuItemClickListener = {
            launchFragmentMenuBludo(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            (requireActivity() as MainActivity).setCurrentFragment(R.id.navigation_main)
            remove()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = null
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
        setAdapter()
        setAdapterClick()
    }

    private fun launchFragmentMenuBludo(categoryId: Int) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_main, FragmentMenuBludo.newInstance(categoryId))
            .addToBackStack(FragmentMenuBludo.NAME)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val NAME = "FragmentMenu"
        fun newInstance(): FragmentMenuCategory {
            return FragmentMenuCategory()
        }
    }
}