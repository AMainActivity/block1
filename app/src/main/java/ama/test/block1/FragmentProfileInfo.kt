package ama.test.block1

import ama.test.block1.ProfileRepository.Companion.userPhoto
import ama.test.block1.databinding.ActivityProfileInfoBinding
import android.content.Intent
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
import androidx.fragment.app.viewModels


class FragmentProfileInfo : Fragment() {

    private var _binding: ActivityProfileInfoBinding? = null
    private val viewModel: ProfileViewModel by activityViewModels()
    private val binding
        get() = _binding ?: throw RuntimeException("ActivityProfileInfoBinding == null")

    private fun setUserPhoto(uri: Uri?) {
        if (uri == null) {
            binding.profilePhoto.setImageResource(R.mipmap.notphoto)
        } else {
            binding.profilePhoto.setImageURI(uri)
        }
    }

    override fun onResume() {
        super.onResume()
        val userPhoto = ProfileRepository.profilePreference(requireContext()).userPhoto
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

        _binding = ActivityProfileInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                startActivity(Intent(requireContext(), ProfileEditActivity::class.java))
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