package com.photosurfer.android

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.photosurfer.android.adapter.MainViewPagerAdapter
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.databinding.ActivityMainBinding
import com.photosurfer.android.main.HomeFragment
import com.photosurfer.android.shared.R.color.login_blue_top
import com.photosurfer.android.shared.R.color.white
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var mainViewPagerAdapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        syncBottomNavWithVp()
        initStatusBarColor()
        changeStatusBarColor()
    }

    private fun initAdapter() {
        binding.vpMain.adapter = MainViewPagerAdapter(this).also { mainViewPagerAdapter = it }
        mainViewPagerAdapter.fragmentList = listOf(
            // TODO : HomeFragment(), TagFragment(), AlarmFragment(), SettingFragment()로 교체할것
            HomeFragment(), HomeFragment(), HomeFragment(), HomeFragment()
        )
    }

    private fun initStatusBarColor() {
        this.window.statusBarColor = ContextCompat.getColor(this, login_blue_top)
    }

    private fun changeStatusBarColor() {
        binding.bottomNav.setOnItemSelectedListener {
            val colorRes = if (it.itemId == R.id.menu_home) login_blue_top else white
            this.window.statusBarColor = ContextCompat.getColor(this, colorRes)
            return@setOnItemSelectedListener true
        }
    }

    private fun syncBottomNavWithVp() {
        binding.vpMain.isUserInputEnabled = false
        binding.bottomNav.setOnItemSelectedListener {
            val position = when (it.itemId) {
                R.id.menu_home -> 0
                R.id.menu_tag -> 1
                R.id.menu_alarm -> 2
                R.id.menu_setting -> 3
                else -> throw IllegalStateException("Wrong Position")
            }
            binding.vpMain.setCurrentItem(position, false)
            return@setOnItemSelectedListener true
        }
    }
}
