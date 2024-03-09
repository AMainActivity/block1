package ama.test.block1

import ama.test.block1.UI.FragmentAkcii
import ama.test.block1.UI.FragmentMain
import ama.test.block1.UI.FragmentMenu
import ama.test.block1.UI.FragmentOrders
import ama.test.block1.UI.FragmentProfileMain
import ama.test.block1.databinding.ActivityMainBinding
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentManager


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomNavigation()
    }

    private fun setBottomNavigation() {
        // val orgFragment = FragmentMain()
        setCurrentFragment(R.id.navigation_main)
        binding.contentMain.bottomNavigationView.setOnItemSelectedListener {
            setCurrentFragment(
                it.itemId
            )
            true
        }
    }


    private fun showBottomNav() {
        binding.contentMain.bottomNavigationView.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding.contentMain.bottomNavigationView.visibility = View.GONE
    }

    private fun selectBottomMenuById(menuId: Int) {
        (binding.contentMain.bottomNavigationView.menu.findItem(menuId)).setChecked(true)
    }

    fun setCurrentFragment(menuId: Int) {

        var tagName = ""
        if (menuId == R.id.navigation_main) supportFragmentManager.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        supportFragmentManager.beginTransaction().apply {
            replace(
                R.id.nav_host_fragment_content_main, when (menuId) {
                    R.id.navigation_main -> {
                        hideBottomNav()
                        tagName = FragmentMain.NAME
                        FragmentMain.newInstance()
                    }

                    R.id.navigation_menu -> {
                        showBottomNav()
                        tagName = FragmentMenu.NAME
                        FragmentMenu.newInstance()
                    }

                    R.id.navigation_akcii -> {
                        showBottomNav()
                        tagName = FragmentAkcii.NAME
                        FragmentAkcii.newInstance()
                    }

                    R.id.navigation_orders -> {
                        showBottomNav()
                        tagName = FragmentOrders.NAME
                        FragmentOrders.newInstance()
                    }

                    R.id.navigation_delivery -> {
                        showBottomNav()
                        tagName = FragmentProfileMain.NAME
                        FragmentProfileMain.newInstance()
                    }

                    else -> FragmentMain()
                }
            )
            //addToBackStack(tagName)
            commit()
        }
        selectBottomMenuById(menuId)
    }
}
