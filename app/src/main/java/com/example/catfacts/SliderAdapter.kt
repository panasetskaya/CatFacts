package com.example.catfacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SliderAdapter(fragment:FragmentActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = SliderFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT,position)
        }
        return fragment
    }
}