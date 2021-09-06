package com.clipcodetest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clipcodetest.R
import com.clipcodetest.models.UserInfo
import kotlin.collections.ArrayList

class UsersAdapter(
    private val context : Context,
    private var items : ArrayList<UserInfo>?,
    private var listener : OnItemClicked
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var isSkeleton : Boolean = true
    private val SKELETON_TYPE = -1
    interface OnItemClicked {
        fun onItemClicked(itemClick: UserInfo?, position: Int)
    }

    fun setItems(items : ArrayList<UserInfo>?){
        isSkeleton = items==null
        this.items = items
    }

    override fun getItemViewType(position: Int): Int {
        return if(isSkeleton){
            -1
        }else{
            1
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (viewType == SKELETON_TYPE) {
            ViewHolderSeleton(
                (LayoutInflater.from(context).inflate(
                    R.layout.clip_code_test_main_skeleton,
                    parent, false
                ))
            )
        } else {
            ViewHolder(
                (LayoutInflater.from(context).inflate(
                    R.layout.clip_code_test_item_user,
                    parent, false
                ))
            )
        }
    }

    override fun getItemCount(): Int {
        if(items!=null){
            items?.let {
                return it.size
            }
        }else{
            return 1
        }
        return 0
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        if(items==null){
            onBindViewSkeletonHolder(holder as ViewHolderSeleton)
        }else{
            onBindViewUserHolder(holder as ViewHolder, position)
        }

    }

    private fun onBindViewSkeletonHolder(
        holder: ViewHolderSeleton) {
        holder.title.text = context.getString(R.string.skeleton)
    }

    private fun onBindViewUserHolder(holder: ViewHolder, position: Int) {

        items?.let{ _items ->
            val item = _items[position]
            item?.let{
                holder.tvUserName.text = it.name?.getCompleteName()
                holder.tvNacionality.text = it.location?.country
                Glide.with(context).load(it.picture?.large)
                    .into(holder.ivUser)
                handleClick(holder.itemView, item,position)
            }
        }
    }

    private fun handleClick(maimClickedView: View, item: UserInfo, position: Int) {
        maimClickedView.setOnClickListener {
            item?.let {
                listener.onItemClicked(it, position)
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvUserName: TextView = view.findViewById(R.id.tvNameUser)
        val tvNacionality : TextView = view.findViewById(R.id.tvNacionalityUser)
        val ivUser: ImageView = view.findViewById(R.id.ivUser)

    }

    class ViewHolderSeleton(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
    }

}