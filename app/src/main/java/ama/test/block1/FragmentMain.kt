package ama.test.block1

import ama.test.block1.databinding.FragmentMainBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        val menuIyemList = MenuItem.createMenuItemList()
        val adapter = MainAdapter(menuIyemList)
        binding.recyclerView.adapter = adapter
        adapter.onMenuItemClickListener = {
            // (requireActivity() as MainActivity).selectBottomMenuById(it)
            (requireActivity() as MainActivity).setCurrentFragment(it)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val NAME = "FragmentMain"
        fun newInstance(): FragmentMain {
            return FragmentMain()
        }
    }
}