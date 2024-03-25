package ama.test.block1.UI.akcii

import ama.test.block1.R
import ama.test.block1.databinding.FragmentDialogAkciaInfoBinding
import ama.test.block1.entyty.DataAkcii
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.squareup.picasso.Picasso
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime


class FragmentDialogAkciaInfo : DialogFragment() {
    private var _binding: FragmentDialogAkciaInfoBinding? = null

    private var dataAkcia: DataAkcii? = null
    private val binding: FragmentDialogAkciaInfoBinding
        get() = _binding ?: throw RuntimeException("FragmentDialogTakePhoto == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogAkciaInfoBinding.inflate(inflater, container, false)
        setDialogAttributes()
        return binding.root
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

    override fun onStart() {
        super.onStart()
        setDialogLayout()
    }

    private fun setDialogLayout() {
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog?.window?.setLayout(width, height)
    }

    private fun setDialogAttributes() {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.akciaTvDate.text = getFormattedDate(dataAkcia?.dateStart)
        binding.akciaTvTitle.text = dataAkcia?.name.toString()
        binding.akciaTvInfo.text = dataAkcia?.description.toString()
        Picasso.get().load(dataAkcia?.urlImage)
            .into(binding.akciaIv)
        binding.imageButtonClose.setOnClickListener {
            dialog?.dismiss()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        val args = requireArguments()
        if (!args.containsKey(ARG_DATA_AKCIA)) {
            throw RuntimeException(FragmentItemAkcia.PARSE_ERROR)
        }
        args.getParcelable<DataAkcii>(ARG_DATA_AKCIA)?.let {
            dataAkcia = it
        }
    }

    companion object {
        private const val ZERO_LONG = 0L
        private const val SPACE_STRING = " "
        private const val SPACE_CHAR = ' '
        private const val ARG_DATA_AKCIA = "data_akcia"
        const val PARSE_ERROR = "Required param dataList is absent"
        const val NAME = "FragmentDialogAkciaInfo"
        fun newInstance(dataAkcia: DataAkcii): FragmentDialogAkciaInfo {
            return FragmentDialogAkciaInfo().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_DATA_AKCIA, dataAkcia)
                }
            }
        }
    }
}