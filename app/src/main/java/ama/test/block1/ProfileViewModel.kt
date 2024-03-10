package ama.test.block1

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    private val _profilePhoto = MutableLiveData<Uri?>()
    val profilePhoto: LiveData<Uri?> = _profilePhoto

    fun changeUriFromCamera(uri: Uri?) {
        _profilePhoto.value = uri
    }
}