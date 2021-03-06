package com.photosurfer.android

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.photosurfer.android.adapter.MainViewPagerAdapter
import com.photosurfer.android.alarm_list.AlarmListMainFragment
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.databinding.ActivityMainBinding
import com.photosurfer.android.main.TagFragment
import com.photosurfer.android.main.home.HomeFragment
import com.photosurfer.android.main.setting.SettingFragment
import com.photosurfer.android.shared.R.color.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var mainViewPagerAdapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        onClickBottomNavigation()
        initStatusBarColor()
        disableViewPagerSwipe()
    }

    private fun initAdapter() {
        binding.vpMain.adapter = MainViewPagerAdapter(this).also { mainViewPagerAdapter = it }
        mainViewPagerAdapter.fragmentList = listOf(
            HomeFragment(), TagFragment(), AlarmListMainFragment(), SettingFragment()
        )
    }

    private fun disableViewPagerSwipe() {
        binding.vpMain.isUserInputEnabled = false
    }

    private fun initStatusBarColor() {
        this.window.statusBarColor = ContextCompat.getColor(this, home_status_bar_color)
    }

    private fun onClickBottomNavigation() {
        binding.bottomNav.setOnItemSelectedListener {
            val position = when (it.itemId) {
                R.id.menu_home -> 0
                R.id.menu_tag -> 1
                R.id.menu_alarm -> 2
                R.id.menu_setting -> 3
                else -> throw IllegalStateException("Wrong Position")
            }
            setStatusBarColor(position)
            syncBottomNavWithVp(position)
            return@setOnItemSelectedListener true
        }
    }

    private fun setStatusBarColor(position: Int) {
        val colorRes = when (position) {
            0 -> home_status_bar_color
            1, 3 -> white
            2 -> alarm_grad_blue_top
            else -> throw IllegalStateException("Wrong Position")
        }
        this.window.statusBarColor = ContextCompat.getColor(this, colorRes)
    }

    private fun syncBottomNavWithVp(position: Int) {
        binding.vpMain.setCurrentItem(position, false)
    }

}
