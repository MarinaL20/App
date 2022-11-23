package com.example.app.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class Adapter(frAct: FragmentActivity, private val list: List<Fragment>) : FragmentStateAdapter(frAct) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        //изначення позиції (е для того, щоб визначити який фрагмент відобразити)
        return list[position]

    }


}