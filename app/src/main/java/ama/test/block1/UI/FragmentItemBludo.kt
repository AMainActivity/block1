package ama.test.block1.UI

import ama.test.block1.MyApp
import ama.test.block1.R
import ama.test.block1.databinding.FragmentItemBludoBinding
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso


class FragmentItemBludo : Fragment() {

    private var _binding: FragmentItemBludoBinding? = null
    private var dataMenuBludo: DataMenuBludo? = null
    private var kolvo: Int = 1
    private var categoryName: String = ""
    private lateinit var adapter: FragmentMenuBludoAdapter
    private val dataList: List<DataMenuBludo> by lazy {
        (requireActivity().application as MyApp).dataMenuBludo
    }
    private val dataCategoryList: List<DataMenuCategory> by lazy {
        (requireActivity().application as MyApp).dataMenuCategory
    }
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentItemBludoBinding == null")


    private fun setAdapter() {
        adapter = FragmentMenuBludoAdapter()
        binding.frhmntMenuRv.adapter = adapter
        binding.frhmntMenuRv.itemAnimator = null
        adapter.submitList(
            dataList.filter { it.categoryId == dataMenuBludo?.categoryId }.toList().shuffled()
                .take(2).toList()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentItemBludoBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = null
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true);
        binding.toolbarTitle.text = categoryName
        Picasso.get().load(dataMenuBludo?.urlImage)
            .into(binding.ivBludo)
        binding.tvBludoName.text = dataMenuBludo?.name
        binding.tvBludoDescription.text = dataMenuBludo?.description
        binding.tvBludoCalory.text = dataMenuBludo?.calory
        binding.tvBludoCena.text = dataMenuBludo?.cena
        binding.tvBludoMassa.text = dataMenuBludo?.massa
        binding.tvBludoSostav.text = dataMenuBludo?.sostav
        binding.tvKolvo.text = kolvo.toString()
        binding.tvBludoLimit.text = String.format(
            getString(R.string.bludo_item_limit_format),
            kolvo
        )
        binding.butPlus.setOnClickListener {
            var kolvo = binding.tvKolvo.text.toString().toInt()
            if (kolvo < 15) kolvo++
            binding.tvKolvo.text = kolvo.toString()
            binding.tvBludoLimit.text = String.format(
                getString(R.string.bludo_item_limit_format),
                binding.tvKolvo.text.toString().toInt()
            )
        }
        binding.butMinus.setOnClickListener {
            var kolvo = binding.tvKolvo.text.toString().toInt()
            if (kolvo > 1) kolvo--
            binding.tvKolvo.text = kolvo.toString()
            binding.tvBludoLimit.text = String.format(
                getString(R.string.bludo_item_limit_format),
                binding.tvKolvo.text.toString().toInt()
            )
        }
        setAdapter()
        setAdapterClick()
    }

    private fun setAdapterClick() {
        adapter.onMenuItemClickListener = {
            launchFragmentItemBludo(it)
        }
    }

    private fun launchFragmentItemBludo(data: Pair<Int, DataMenuBludo>) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(
                R.id.nav_host_fragment_content_main,
                FragmentItemBludo.newInstance(
                    categoryName,
                    data.first,
                    data.second
                )
            )
            .addToBackStack(FragmentItemBludo.NAME)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        val args = requireArguments()
        if (!args.containsKey(ARG_DATA_ITEM_BLUDO)
            && !args.containsKey(ARG_DATA_ITEM_BLUDO_KOLVO)
            && !args.containsKey(
                ARG_DATA_ITEM_BLUDO_CATEGORY
            )
        ) {
            throw RuntimeException(PARSE_ERROR)
        }
        args.getParcelable<DataMenuBludo>(ARG_DATA_ITEM_BLUDO)?.let {
            dataMenuBludo = it
        }
        kolvo = args.getInt(ARG_DATA_ITEM_BLUDO_KOLVO)
        categoryName = args.getString(ARG_DATA_ITEM_BLUDO_CATEGORY).toString()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                requireActivity().supportFragmentManager.popBackStack()
                Log.e("home", "home")
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val ARG_DATA_ITEM_BLUDO = "data_item_bludo"
        private const val ARG_DATA_ITEM_BLUDO_KOLVO = "data_item_bludo_kolvo"
        private const val ARG_DATA_ITEM_BLUDO_CATEGORY = "data_item_bludo_category"
        const val PARSE_ERROR = "Required params is absent"
        const val NAME = "FragmentItemBludo"
        fun newInstance(
            categoryName: String,
            kolvo: Int,
            dataMenuBludo: DataMenuBludo
        ): FragmentItemBludo {
            return FragmentItemBludo().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_DATA_ITEM_BLUDO, dataMenuBludo)
                    putInt(ARG_DATA_ITEM_BLUDO_KOLVO, kolvo)
                    putString(ARG_DATA_ITEM_BLUDO_CATEGORY, categoryName)
                }
            }
        }
    }
}