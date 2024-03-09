package ama.test.block1.UI

import ama.test.block1.R
import ama.test.block1.databinding.FragmentProfileEditBinding
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class FragmentProfileEdit : Fragment() {

    private var _binding: FragmentProfileEditBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentProfileEditBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileEditBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = null

        showHideExpandableLayoutPassword()
        setTouchListenerForNewPass()
        setTouchListenerForReplyPass()
    }

    private fun setExpandImageSrs(resId: Int) {
        binding.ivExpandIcon.setImageResource(resId)
    }

    private fun showHideExpandableLayoutPassword() {
        with(binding)
        {
            llExpandChangePass.visibility = View.GONE
            profileEditChangePass.setOnClickListener {
                llExpandChangePass.visibility = if (llExpandChangePass.isShown) {
                    slideUp(llExpandChangePass)
                    setExpandImageSrs(R.drawable.profile_edit_expand_more_24)
                    View.GONE
                } else {
                    slideDown(llExpandChangePass)
                    scrollViewEdit.post {
                        scrollViewEdit.fullScroll(ScrollView.FOCUS_DOWN)
                    }
                    setExpandImageSrs(R.drawable.profile_edit_pass_expand_less_24)
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun slideUp(v: View?) {
        val a: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)
        a.reset()
        if (v != null) {
            v.clearAnimation()
            v.startAnimation(a)
        }
    }

    private fun slideDown(v: View?) {
        val a: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_down)
        a.reset()
        if (v != null) {
            v.clearAnimation()
            v.startAnimation(a)
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