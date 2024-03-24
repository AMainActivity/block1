package ama.test.block1.UI

import ama.test.block1.MyApp
import ama.test.block1.R
import ama.test.block1.databinding.FragmentMenuBludoBinding
import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat.generateViewId
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton


class FragmentMenuBludo : Fragment() {

    private var _binding: FragmentMenuBludoBinding? = null
    private lateinit var adapter: FragmentMenuBludoAdapter
    private var categoryId: Int = 0
    private val dataList: List<DataMenuBludo> by lazy {
        (requireActivity().application as MyApp).dataMenuBludo
    }
    private val dataCategoryList: List<DataMenuCategory> by lazy {
        (requireActivity().application as MyApp).dataMenuCategory
    }
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentMenuBludoBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.category_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setAdapter() {
        adapter = FragmentMenuBludoAdapter()
        binding.frhmntMenuRv.adapter = adapter
        binding.frhmntMenuRv.itemAnimator = null
        adapter.submitList(dataList.filter { it.categoryId == categoryId }.toList())


    }

    private fun setAdapterClick() {
        adapter.onMenuItemClickListener = {
            launchFragmentItemBludo(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMenuBludoBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = null

        setAdapter()
        setAdapterClick()


        val ll = binding.llFilterCategory
        val stateManager = StateManager(requireContext())

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(10, 10, 18 * stateManager.margDp(), 10)
        dataCategoryList.forEach { dataMenuCategory ->
            val b = MaterialButton(requireContext())
            b.id = generateViewId()
            b.layoutParams = params
            b.cornerRadius = 70 * stateManager.margDp()
            b.isAllCaps = false
            b.letterSpacing = 0f
            b.text = dataMenuCategory.name
            stateManager.addCardToList(dataMenuCategory.id, b)
            ll.addView(b)
            b.setOnClickListener {
                categoryId = dataMenuCategory.id
                stateManager.notifyStateChanged(it.id)
                adapter.submitList(dataList.filter { dataModel -> dataModel.categoryId == dataMenuCategory.id }
                    .toList())
            }
        }
        stateManager.checkButtonById(categoryId)
    }

    private fun launchFragmentItemBludo(data: Pair<Int, DataMenuBludo>) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(
                R.id.nav_host_fragment_content_main,
                FragmentItemBludo.newInstance(
                    dataCategoryList.first { it.id == categoryId }.name,
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
        if (!args.containsKey(ARG_DATA_CATEGORY_ID)) {
            throw RuntimeException(PARSE_ERROR)
        }
        categoryId = args.getInt(ARG_DATA_CATEGORY_ID)
    }

    class StateManager(context: Context) {
        private val ctx = context
        fun margDp() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            1f,
            ctx.resources.displayMetrics
        ).toInt()

        private val listOgCard = mutableListOf<Pair<Int, MaterialButton>>()
        fun addCardToList(categoryId: Int, button: MaterialButton) {
            listOgCard.add(categoryId to button)
        }

        fun checkButtonById(categoryId: Int) {
            val button = listOgCard.first { it.first == categoryId }.second
            notifyStateChanged(button.id)
        }

        fun notifyStateChanged(id: Int) {
            listOgCard.forEach {
                val b = it.second

                if (it.second.id != id) {
                    b.setStrokeColorResource(R.color.transparent)
                    b.backgroundTintList =
                        ContextCompat.getColorStateList(
                            ctx,
                            R.color.transparent
                        )
                    b.strokeWidth = 0
                } else {
                    b.setStrokeColorResource(R.color.orange)
                    b.backgroundTintList =
                        ContextCompat.getColorStateList(
                            ctx,
                            R.color.orange_transparently
                        )
                    b.strokeWidth = 2 * margDp()
                }
            }
        }
    }

    companion object {
        private const val ARG_DATA_CATEGORY_ID = "data_categoty_id"
        const val PARSE_ERROR = "Required param categotyId is absent"
        const val NAME = "FragmentMenuBludo"
        fun newInstance(categoryId: Int): FragmentMenuBludo {
            return FragmentMenuBludo().apply {
                arguments = Bundle().apply {
                    putInt(ARG_DATA_CATEGORY_ID, categoryId)
                }
            }
        }
    }
}