package ama.test.block1

import ama.test.block1.databinding.ActivityProfileEditBinding
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen


class ProfileEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityProfileEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = null
        showHideExpandableLayoutPassword()
        setTouchListenerForNewPass()
        setTouchListenerForReplyPass()
    }

    private fun setexpandImageSrs(resId: Int) {
        binding.ivExpandIcon.setImageResource(resId)
    }

    private fun showHideExpandableLayoutPassword() {
        with(binding)
        {
            llExpandChangePass.visibility = View.GONE
            profileEditChangePass.setOnClickListener {
                llExpandChangePass.visibility = if (llExpandChangePass.isShown) {
                    slideUp(llExpandChangePass)
                    setexpandImageSrs(R.drawable.profile_edit_expand_more_24)
                    View.GONE
                } else {
                    slideDown(llExpandChangePass)
                    scrollViewEdit.post {
                        scrollViewEdit.fullScroll(ScrollView.FOCUS_DOWN)
                    }
                    setexpandImageSrs(R.drawable.profile_edit_pass_expand_less_24)
                    View.VISIBLE
                }
            }
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchListenerForNewPass() {
        binding.tvShowNewPass.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.etNewPass.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()

                }

                MotionEvent.ACTION_UP -> {
                    binding.etNewPass.transformationMethod =
                        PasswordTransformationMethod.getInstance()
                }
            }
            v?.onTouchEvent(event) ?: true
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchListenerForReplyPass() {
        binding.tvShowReplyPass.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.etReplyPass.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()

                }

                MotionEvent.ACTION_UP -> {
                    binding.etReplyPass.transformationMethod =
                        PasswordTransformationMethod.getInstance()
                }
            }
            v?.onTouchEvent(event) ?: true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun slideUp(v: View?) {
        val a: Animation = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        a.reset()
        if (v != null) {
            v.clearAnimation()
            v.startAnimation(a)
        }
    }

    private fun slideDown(v: View?) {
        val a: Animation = AnimationUtils.loadAnimation(this, R.anim.slide_down)
        a.reset()
        if (v != null) {
            v.clearAnimation()
            v.startAnimation(a)
        }
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
