package com.photosurfer.android.navigator

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity

interface MainNavigator {

    fun navigateMain(context: Context)

    fun transactionPushMainFragment(fragmentActivity: FragmentActivity, bundle: Bundle)
}
