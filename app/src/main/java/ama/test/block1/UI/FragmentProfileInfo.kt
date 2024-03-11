package ama.test.block1.UI

import ama.test.block1.ProfilePreferences
import ama.test.block1.ProfilePreferences.Companion.userPhoto
import ama.test.block1.ProfileViewModel
import ama.test.block1.R
import ama.test.block1.databinding.FragmentProfileInfoBinding
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels


class FragmentProfileInfo : Fragment() {

    private var _binding: FragmentProfileInfoBinding? = null
    private val viewModel: ProfileViewModel by activityViewModels()
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentProfileInfoBinding == null")

    private fun setUserPhoto(uri: Uri?) {
        if (uri == null) {
            binding.profilePhoto.setImageResource(R.mipmap.notphoto)
        } else {
            binding.profilePhoto.setImageURI(uri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        val userPhoto = ProfilePreferences.profilePreference(requireContext()).userPhoto
        setUserPhoto(
            if (userPhoto.isNotEmpty()) Uri.parse(
                userPhoto
            ) else null
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.info_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun launchFragmentProfileEdit() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_main, FragmentProfileEdit.newInstance())
            .addToBackStack(FragmentProfileEdit.NAME)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                launchFragmentProfileEdit()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.profilePhoto.observe(viewLifecycleOwner)
        {
            setUserPhoto(it)
        }
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = null
        binding.imageButtonChangePhoto.setOnClickListener {
            FragmentProfilePhoto.newInstance().show(
                (requireActivity() as AppCompatActivity).supportFragmentManager,
                FragmentProfilePhoto.NAME
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val NAME = "FragmentProfileInfo"
        fun newInstance(): FragmentProfileInfo {
            return FragmentProfileInfo()
        }
    }
}