package ama.test.block1

import ama.test.block1.ProfileRepository.Companion.userPhoto
import ama.test.block1.databinding.ActivityProfileMainBinding
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class FragmentProfile : Fragment() {

    private var _binding: ActivityProfileMainBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("ActivityProfileMainBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ActivityProfileMainBinding.inflate(inflater, container, false)
        return binding.root
    }

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

    private fun launchFragmentProfileInfo() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_main, FragmentProfileInfo.newInstance())
            .addToBackStack(FragmentProfileInfo.NAME)
            .commit()
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
        binding.nestedScroll.menuPersonData.setOnClickListener {
            launchFragmentProfileInfo()//startActivity(Intent(requireContext(), ProfileInfoActivity::class.java))
        }
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
        const val NAME = "FragmentProfile"
        fun newInstance(): FragmentProfile {
            return FragmentProfile()
        }
    }
}