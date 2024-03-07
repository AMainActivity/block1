package ama.test.block1

import ama.test.block1.ProfileRepository.Companion.userPhoto
import ama.test.block1.databinding.ActivityProfileMainBinding
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class ProfileMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileMainBinding
    private fun setUserPhoto(uri: Uri?) {
        if (uri == null) {
            binding.profilePhoto.setImageResource(R.mipmap.notphoto)
        } else {
            binding.profilePhoto.setImageURI(uri)
            /*val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(
                    ImageDecoder.createSource(
                        contentResolver,
                        uri
                    )
                )
            } else {
                MediaStore.Images.Media.getBitmap(
                    contentResolver,
                    uri
                )
            }

            binding.profilePhoto.setImageBitmap(
                bitmap.copy(
                    Bitmap.Config.ARGB_8888,
                    false
                ).toCircular()
            )*/
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
        binding = ActivityProfileMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = null
        binding.nestedScroll.menuPersonData.setOnClickListener {
            startActivity(Intent(this, ProfileInfoActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}
