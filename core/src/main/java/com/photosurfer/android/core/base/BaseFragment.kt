package com.photosurfer.android.core.base

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.photosurfer.android.core.util.EventObserver
import com.photosurfer.android.core.util.Injector
import com.photosurfer.android.navigator.MainNavigator
import dagger.hilt.android.EntryPointAccessors

abstract class BaseFragment<T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : Fragment() {
    private var _binding: T? = null
    protected val binding: T
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    private val mainNavigator: MainNavigator by lazy {
        EntryPointAccessors.fromActivity(
            requireActivity(),
            Injector.MainNavigatorInjector::class.java
        ).mainNavigator()
    }

    private val sharedPreferences: SharedPreferences by lazy {
        EntryPointAccessors.fromActivity(
            requireActivity(),
            Injector.SharedPreferencesInjector::class.java
        ).sharedPreferences()
    }

    fun terminationTokenHandling(viewModel: BaseViewModel) {
        viewModel.moveToLogin.observe(
            viewLifecycleOwner,
            EventObserver {
//            mainNavigator.openLogin(requireActivity())  이부분 추후에 작성해야함 로그인화면으로 이동부분
                sharedPreferences.edit().remove("PHOTO_SURFER_ACCESS_TOKEN").apply()
                sharedPreferences.edit().remove("USER_NICKNAME").apply()
                requireActivity().finish()
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
