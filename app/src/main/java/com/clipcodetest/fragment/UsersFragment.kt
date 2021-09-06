package com.clipcodetest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clipcodetest.R
import com.clipcodetest.activity.MainActivity
import com.clipcodetest.adapter.UsersAdapter
import com.clipcodetest.databinding.ClipCodeTestFragmentUsersBinding
import com.clipcodetest.fragment.base.BaseFragment
import com.clipcodetest.models.UserInfo
import com.clipcodetest.models.UserResponse
import com.clipcodetest.viewmodel.UsersViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : BaseFragment(), UsersAdapter.OnItemClicked {

    private val usersViewModel: UsersViewModel by viewModel()
    private lateinit var binding: ClipCodeTestFragmentUsersBinding
    private lateinit var usersAdapter: UsersAdapter
    private var userItem : ArrayList<UserInfo>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = ClipCodeTestFragmentUsersBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disableOnBackPressed()
        initAdapter()
        initRecyclerView()
        initObservers()
        (activity as MainActivity).loader.show()
        usersViewModel.loadUser()
    }

    private fun initAdapter(){
        usersAdapter = UsersAdapter(requireContext(), userItem , this)
    }

    private fun initRecyclerView(){
        binding.recyclerView?.run {
            onFlingListener = null
            val controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);
            layoutAnimation = controller
            adapter = usersAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        }

    }

    private fun initObservers(){
        val usersDataObserver = Observer<ArrayList<UserResponse>>{ userList ->
            (activity as MainActivity).loader.dismiss()
            if(userList != null){
                userList.forEach {
                    userItem?.add(it.dataUser[0])
                }
                usersAdapter?.apply {
                    if (!userItem.isNullOrEmpty()) {
                        setItems(userItem)
                    } else {
                        setItems(null)
                    }
                    notifyDataSetChanged()
                    binding.recyclerView?.scheduleLayoutAnimation()
                }
            }
        }
        usersViewModel.userList.observe(viewLifecycleOwner,usersDataObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        removeObservers()
    }

    private fun removeObservers(){
        usersViewModel.userList.value = null
    }

    override fun onItemClicked(itemClick: UserInfo?, position: Int) {
        val action = UsersFragmentDirections.actionUsersFragmentToUserDetailFragment(personalData = Gson().toJson(itemClick))
        findNavController().navigate(action)
    }
}
