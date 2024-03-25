package ama.test.block1.UI.akcii

import ama.test.block1.MainActivity
import ama.test.block1.R
import ama.test.block1.databinding.FragmentItemAkciaBinding
import ama.test.block1.entyty.DataAkcii
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime


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
        binding.akciaTvDate.text = getFormattedDate(dataAkcia?.dateStart)
        binding.akciaTvTitle.text = dataAkcia?.name.toString()
        Picasso.get().load(dataAkcia?.urlImage)
            .into(binding.akciaIv)
        binding.akciaCv.setOnClickListener {
            FragmentDialogAkciaInfo.newInstance(dataAkcia ?: throw Exception(ERROR_NO_DATA))
                .show(
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

    private val rusMonthsName: MonthNames = MonthNames(
        listOf(
            "января", "февраля", "марта", "апреля", "мая", "июня",
            "июля", "августа", "сентября", "октября", "ноября", "декабря"
        )
    )

    private fun getFormattedDate(dateTime: Long?): String {
        val instant = Instant.fromEpochMilliseconds(dateTime ?: ZERO_LONG)
        return instant.toLocalDateTime(TimeZone.currentSystemDefault()).date.format(LocalDate.Format {
            dayOfMonth()
            char(SPACE_CHAR)
            monthName(rusMonthsName)
            chars(SPACE_STRING)
            year()
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    companion object {
        private const val ARG_DATA_ITEM_AKCIA = "data_item_akcia"
        private const val ZERO_LONG = 0L
        private const val SPACE_STRING = " "
        private const val SPACE_CHAR = ' '
        private const val ERROR_NO_DATA = "не на что смотреть"
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