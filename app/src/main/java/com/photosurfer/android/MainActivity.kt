package com.photosurfer.android

import android.os.Bundle
import com.photosurfer.android.adapter.MainViewPagerAdapter
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.databinding.ActivityMainBinding
import com.photosurfer.android.main.HomeFragment

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var mainViewPagerAdapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        syncBottomNavWithVp()
    }

    private fun initAdapter() {
        binding.vpMain.adapter = MainViewPagerAdapter(this).also { mainViewPagerAdapter = it }
        mainViewPagerAdapter.fragmentList = listOf(
            // TODO : HomeFragment(), TagFragment(), AlarmFragment(), SettingFragment()로 교체할것
            HomeFragment(), HomeFragment(), HomeFragment(), HomeFragment()
        )
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
