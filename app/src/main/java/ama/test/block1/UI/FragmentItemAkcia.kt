package ama.test.block1.UI

import ama.test.block1.DataAkcii
import ama.test.block1.MainActivity
import ama.test.block1.R
import ama.test.block1.databinding.FragmentItemAkciaBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso


class FragmentItemAkcia : Fragment() {

    private var _binding: FragmentItemAkciaBinding? = null
    private var dataAkcia: DataAkcii? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentItemAkciaBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentItemAkciaBinding.inflate(inflater, container, false)
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
        binding.akciaTvDate.text = dataAkcia?.dateStart.toString()
        binding.akciaTvTitle.text = dataAkcia?.name.toString()
        Picasso.get().load(dataAkcia?.urlImage)
            .into(binding.akciaIv)
        binding.akciaCv.setOnClickListener {
            FragmentDialogAkciaInfo.newInstance(dataAkcia?:throw Exception("не на что смотреть")).show(
                childFragmentManager, FragmentDialogAkciaInfo.NAME
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        val args = requireArguments()
        if (!args.containsKey(ARG_DATA_ITEM_AKCIA)) {
            throw RuntimeException(PARSE_ERROR)
        }
        args.getParcelable<DataAkcii>(ARG_DATA_ITEM_AKCIA)?.let {
            dataAkcia = it
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    companion object {
        private const val ARG_DATA_ITEM_AKCIA = "data_item_akcia"
        const val PARSE_ERROR = "Required param dataList is absent"
        const val NAME = "FragmentItemAkcia"
        fun newInstance(dataAkcia: DataAkcii): FragmentItemAkcia {
            return FragmentItemAkcia().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_DATA_ITEM_AKCIA, dataAkcia)
                }
            }
        }
    }
}