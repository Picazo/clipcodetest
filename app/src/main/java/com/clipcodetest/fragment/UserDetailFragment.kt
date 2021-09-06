package com.clipcodetest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.clipcodetest.databinding.ClipCodeTestFragmentDetailUserBinding
import com.clipcodetest.fragment.base.BaseFragment
import com.clipcodetest.models.UserInfo
import com.google.gson.Gson

class UserDetailFragment : BaseFragment(){

    private lateinit var binding: ClipCodeTestFragmentDetailUserBinding
    private val args: UserDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = ClipCodeTestFragmentDetailUserBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgs()
    }

    private fun getArgs(){
        val dataUser = Gson().fromJson(args.personalData, UserInfo::class.java)
        binding.apply {
            tvNameUser.text = dataUser.name?.getCompleteName()
            tvCorreo.text = dataUser.email
            tvAdrees.text = dataUser.location?.getStreet()
            tvGenero.text = dataUser.gender
            tvPhone.text = dataUser.phone
            tvCell.text = dataUser.cell
            Glide.with(requireContext()).load(dataUser.picture?.medium)
                .into(ivUser)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}
