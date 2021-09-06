package com.clipcodetest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.clipcodetest.R
import com.clipcodetest.databinding.ClipCodeTestFragmentSplashBinding
import com.clipcodetest.fragment.base.BaseFragment
import com.clipcodetest.viewmodel.GlobalViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment() {

    private val globalViewModel: GlobalViewModel by viewModel()
    private lateinit var binding: ClipCodeTestFragmentSplashBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = ClipCodeTestFragmentSplashBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        globalViewModel.startCountDown()
        disableOnBackPressed()
        initObservers()
    }

    private fun initObservers(){
        val splashDataObserver = Observer<Boolean>{ isCountFinish ->
            if(isCountFinish != null){
                if (isCountFinish){
                    findNavController().navigate(R.id.usersFragment)
                }
            }
        }
        globalViewModel.splashMd.observe(viewLifecycleOwner,splashDataObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        removeObservers()
    }

    private fun removeObservers(){
        globalViewModel.splashMd.value = null
    }


}
