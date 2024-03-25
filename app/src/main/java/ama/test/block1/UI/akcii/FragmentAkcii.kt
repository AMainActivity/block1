package ama.test.block1.UI.akcii

import ama.test.block1.MainActivity
import ama.test.block1.MyApp
import ama.test.block1.R
import ama.test.block1.databinding.FragmentAkciiBinding
import ama.test.block1.entyty.DataAkcii
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.annotation.DimenRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2


class FragmentAkcii : Fragment() {

    private var _binding: FragmentAkciiBinding? = null
    private val dataList: List<DataAkcii> by lazy {
        (requireActivity().application as MyApp).dataAkcii
    }
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentAkciiBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAkciiBinding.inflate(inflater, container, false)
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

        setupPager()
    }

    private fun setupPager() {
        val fragments = dataList.map {
            FragmentItemAkcia.newInstance(it)
        }
        val viewPager = binding.pager
        val adapter = AkciiAdapter(fragments, childFragmentManager, viewLifecycleOwner.lifecycle)
        viewPager.adapter = adapter
        val dotsContainer = binding.dotContainer
        val dotCount = fragments.size
        val dotViews = arrayOfNulls<ImageView>(dotCount)

        for (i in 0 until dotCount) {
            dotViews[i] =
                layoutInflater.inflate(R.layout.indicator_dot, dotsContainer, false) as ImageView
            dotsContainer.addView(dotViews[i])
        }
        val nextItemVisiblePx = requireContext().resources.getDimension(R.dimen.margin_25)
        val currentItemHorizontalMarginPx =
            requireContext().resources.getDimension(R.dimen.margin_25)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
        }
        viewPager.setPageTransformer(pageTransformer)
        val itemDecoration = HorizontalMarginItemDecoration(
            requireContext(),
            R.dimen.margin_25
        )
        viewPager.addItemDecoration(itemDecoration)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                for (i in 0 until dotCount) {
                    if (i == position) {
                        dotViews[i]?.setImageResource(R.drawable.ic_dot_selected)
                    } else {
                        dotViews[i]?.setImageResource(R.drawable.ic_dot_unselected)
                    }
                }
            }
        })
    }

    class HorizontalMarginItemDecoration(context: Context, @DimenRes horizontalMarginInDp: Int) :
        RecyclerView.ItemDecoration() {

        private val horizontalMarginInPx: Int =
            context.resources.getDimension(horizontalMarginInDp).toInt()

        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
        ) {
            outRect.right = horizontalMarginInPx
            outRect.left = horizontalMarginInPx
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        const val NAME = "FragmentAkcii"
        fun newInstance(): FragmentAkcii {
            return FragmentAkcii()
        }
    }
}