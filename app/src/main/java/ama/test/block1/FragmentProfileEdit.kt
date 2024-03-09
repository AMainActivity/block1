package ama.test.block1

import ama.test.block1.ProfileRepository.Companion.userPhoto
import ama.test.block1.databinding.ActivityProfileInfoBinding
import ama.test.block1.databinding.ActivityProfileMainBinding
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class FragmentProfileEdit : Fragment() {

    private var _binding: ActivityProfileInfoBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("ActivityProfileInfoBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ActivityProfileInfoBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = null

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val NAME = "FragmentProfileEdit"
        fun newInstance(): FragmentProfileEdit {
            return FragmentProfileEdit()
        }
    }
}