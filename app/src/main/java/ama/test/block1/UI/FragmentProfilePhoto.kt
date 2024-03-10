package ama.test.block1.UI

import ama.test.block1.ProfilePreferences
import ama.test.block1.ProfilePreferences.Companion.userPhoto
import ama.test.block1.ProfileViewModel
import ama.test.block1.R
import ama.test.block1.databinding.FrgmntDialogProfilePhotoBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FragmentProfilePhoto : BottomSheetDialogFragment() {
    private var _binding: FrgmntDialogProfilePhotoBinding? = null
    private val viewModel: ProfileViewModel by activityViewModels()
    private val binding: FrgmntDialogProfilePhotoBinding
        get() = _binding ?: throw RuntimeException("FrgmntDialogProfilePhotoBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FrgmntDialogProfilePhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getTheme() = R.style.MyTransparentBottomSheetDialogTheme

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profilePhotoCamera.setOnClickListener {
            val d = FragmentDialogTakePhoto.newInstance()
            d.show(
                childFragmentManager, FragmentDialogTakePhoto.NAME
            )
            d.onDialogCreatedListener = {
                dialog?.dismiss()
            }
        }
        binding.profilePhotoDelete.setOnClickListener {
            viewModel.changeUriFromCamera(null)
            ProfilePreferences.profilePreference(requireContext()).userPhoto = ""
            dialog?.dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val NAME = "FragmentProfilePhoto"
        fun newInstance(): FragmentProfilePhoto {
            return FragmentProfilePhoto()
        }
    }
}