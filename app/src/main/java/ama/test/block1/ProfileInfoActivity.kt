package ama.test.block1

import ama.test.block1.ProfileRepository.Companion.userPhoto
import ama.test.block1.databinding.ActivityProfileInfoBinding
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class ProfileInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileInfoBinding
    private val viewModel: ProfileViewModel by viewModels()

    private fun setUserPhoto(uri: Uri?) {
        if (uri == null) {
            binding.profilePhoto.setImageResource(R.mipmap.notphoto)
        } else {
            binding.profilePhoto.setImageURI(uri)
        }
    }

    override fun onResume() {
        super.onResume()
        val userPhoto = ProfileRepository.profilePreference(this).userPhoto
        setUserPhoto(
            if (userPhoto.isNotEmpty()) Uri.parse(
                userPhoto
            ) else null
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityProfileInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.profilePhoto.observe(this)
        {
            setUserPhoto(it)
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = null
        binding.imageButtonChangePhoto.setOnClickListener {
            FragmentProfilePhoto.newInstance().show(
                supportFragmentManager, FragmentProfilePhoto.NAME
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.info_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                startActivity(Intent(this, ProfileEditActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}
