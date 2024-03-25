package ama.test.block1.UI.profile

import ama.test.block1.ProfileViewModel
import ama.test.block1.R
import ama.test.block1.databinding.FrgmntDialogProfilePhotoBinding
import ama.test.block1.utils.ProfilePreferences
import ama.test.block1.utils.ProfilePreferences.Companion.userPhoto
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FragmentProfilePhoto : BottomSheetDialogFragment() {
    private var _binding: FrgmntDialogProfilePhotoBinding? = null
    private val viewModel: ProfileViewModel by activityViewModels()
    private lateinit var launchImageGalery: ActivityResultLauncher<String>
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
        val contractImageGallery = object : ActivityResultContract<String, Uri?>() {
            override fun createIntent(context: Context, input: String): Intent {
                val intent = Intent(Intent.ACTION_PICK)
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT)
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                return Intent(intent).apply { type = input }
            }

            override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
                return intent?.data
            }

        }
        launchImageGalery = registerForActivityResult(contractImageGallery)
        {
            val selectedImageUri = it ?: throw Exception(NO_PHOTO)

            val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
            requireContext().contentResolver.takePersistableUriPermission(selectedImageUri, flag)
            viewModel.changeUriFromCamera(selectedImageUri)
            ProfilePreferences.profilePreference(requireContext()).userPhoto =
                selectedImageUri.toString()

            dialog?.dismiss()
        }
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
        binding.profilePhotoFromGalery.setOnClickListener {
            launchImageGalery.launch(IMAGE_TYPE)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val NAME = "FragmentProfilePhoto"
        const val NO_PHOTO = "no photo"
        const val IMAGE_TYPE = "image/*"
        fun newInstance(): FragmentProfilePhoto {
            return FragmentProfilePhoto()
        }
    }
}