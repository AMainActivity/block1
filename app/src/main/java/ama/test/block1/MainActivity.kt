package ama.test.block1

import ama.test.block1.databinding.ActivityMainBinding
import android.os.Bundle
import  ama.test.block1.MenuItem as ItemMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val menuIyemList = ItemMenu.createMenuItemList()
        val adapter = MainAdapter(menuIyemList)
        binding.recyclerView.adapter = adapter
    }

}
