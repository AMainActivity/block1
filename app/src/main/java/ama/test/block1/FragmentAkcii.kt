package ama.test.block1

import ama.test.block1.databinding.FragmentAkciiBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment


class FragmentAkcii : Fragment() {

    private var _binding: FragmentAkciiBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentAkciiBinding == null")


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
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
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